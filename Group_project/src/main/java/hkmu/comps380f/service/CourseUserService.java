package hkmu.comps380f.service;

import hkmu.comps380f.dao.CourseUserRepository;
import hkmu.comps380f.exception.CourseUserNotFound;
import hkmu.comps380f.model.CourseUser;
import hkmu.comps380f.model.UserRole;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CourseUserService implements UserDetailsService {

    @Resource
    CourseUserRepository courseUserRepo;

    @Transactional
    public List<CourseUser> getUser() {
        return courseUserRepo.findAll();
    }

    @Transactional
    public CourseUser getUser(String Username) {
        return courseUserRepo.findById(Username).orElse(null);
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        CourseUser courseUser = courseUserRepo.findById(username).orElse(null);
        if (courseUser == null) {
            throw new UsernameNotFoundException("User '" + username + "' not found.");
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (UserRole role : courseUser.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getRole()));
        }
        return new User(courseUser.getUsername(), courseUser.getPassword(), authorities);
    }

    @Transactional(rollbackFor = CourseUserNotFound.class)
    public void updateUser(String original_username, String username, String password, String full_name,
            int phone_number, String address) throws IOException, CourseUserNotFound {
        CourseUser updateUser = courseUserRepo.findById(original_username).orElse(null);
        if (updateUser == null) {
            throw new UsernameNotFoundException("User '" + username + "' not found.");
        }
        updateUser.setUsername(username);
        password = "{noop}" + password;
        updateUser.setPassword(password);
        updateUser.setFull_name(full_name);
        updateUser.setPhone_number(phone_number);
        updateUser.setAddress(address);
        courseUserRepo.save(updateUser);
        if (!original_username.equals(username)) {
            CourseUser delete_old_User = courseUserRepo.findById(original_username).orElse(null);
            courseUserRepo.delete(delete_old_User);
        }

    }

}
