package hkmu.comps380f.controller;

import hkmu.comps380f.dao.CourseUserRepository;
import hkmu.comps380f.exception.CourseUserNotFound;
import hkmu.comps380f.model.CourseUser;
import hkmu.comps380f.model.UserRole;
import hkmu.comps380f.service.CourseUserService;
import java.io.IOException;
import java.security.Principal;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/user")
public class CourseUserController {

    @Resource
    CourseUserRepository couserUserRepo;

    @Resource
    CourseUserService courseUserService;

    @GetMapping({"", "/list"})
    public String list(ModelMap model) {
        model.addAttribute("couserUsers", couserUserRepo.findAll());
        return "listUser";
    }

    public static class Form {

        private String username;
        private String password;
        private String full_name;
        private int phone_number;
        private String address;
        private String[] roles;
        private String original_username;
        private List<UserRole> user_roles;

        // ... getters and setters for each of the properties
        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getFull_name() {
            return full_name;
        }

        public void setFull_name(String full_name) {
            this.full_name = full_name;
        }

        public int getPhone_number() {
            return phone_number;
        }

        public void setPhone_number(int phone_number) {
            this.phone_number = phone_number;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String[] getRoles() {
            return roles;
        }

        public void setRoles(String[] roles) {
            this.roles = roles;
        }

        public String getOriginal_username() {
            return original_username;
        }

        public void setOriginal_username(String original_username) {
            this.original_username = original_username;
        }

        public List<UserRole> getUser_roles() {
            return user_roles;
        }

        public void setUser_roles(List<UserRole> user_roles) {
            this.user_roles = user_roles;
        }
    }

    @GetMapping("/create")
    public ModelAndView create() {
        return new ModelAndView("addUser", "courseUser", new Form());
    }

    @PostMapping("/create")
    public View create(Form form) throws IOException {
        CourseUser user = new CourseUser(form.getUsername(),
                form.getPassword(), form.getFull_name(), form.getPhone_number(), form.getAddress(), form.getRoles());
        couserUserRepo.save(user);
        return new RedirectView("/user/list", true);
    }

    @GetMapping("/delete/{username}")
    public View deleteTicket(@PathVariable("username") String username) {
        couserUserRepo.delete(couserUserRepo.findById(username).orElse(null));
        return new RedirectView("/user/list", true);
    }

    @GetMapping("/edit/{username}")
    public ModelAndView showEdit(@PathVariable("username") String username,
            Principal principal, HttpServletRequest request) {
        CourseUser user = courseUserService.getUser(username);
        if (user == null
                || (!request.isUserInRole("ROLE_ADMIN"))) {
            return new ModelAndView(new RedirectView("/home/list", true));
        }
        ModelAndView modelAndView = new ModelAndView("edit_user");
        modelAndView.addObject("user", user);
        Form userForm = new Form();
        userForm.setUsername(user.getUsername());
        String pw = user.getPassword();
        pw = pw.replace("{noop}", "");
        userForm.setPassword(pw);
        userForm.setFull_name(user.getFull_name());
        userForm.setPhone_number(user.getPhone_number());
        userForm.setAddress(user.getAddress());
        userForm.setUser_roles(user.getRoles());
        userForm.setOriginal_username(user.getUsername());
        modelAndView.addObject("userForm", userForm);
        return modelAndView;
    }

    @PostMapping("/edit/{username}")
    public String edit(@PathVariable("username") String username, Form form,
            Principal principal, HttpServletRequest request)
            throws IOException, CourseUserNotFound {
        CourseUser user = courseUserService.getUser(username);
        if (user == null
                || (!request.isUserInRole("ROLE_ADMIN"))) {
            return "redirect:/home/list";
        }
        courseUserService.updateUser(form.getOriginal_username(), form.getUsername(),
                form.getPassword(), form.getFull_name(), form.getPhone_number(), form.getAddress());
        return "redirect:/user";
    }
}
