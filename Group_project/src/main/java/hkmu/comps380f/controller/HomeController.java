package hkmu.comps380f.controller;

import hkmu.comps380f.dao.CourseUserRepository;
import hkmu.comps380f.dao.Lecture_CommentsRepository;
import hkmu.comps380f.dao.PollCommentsRepository;
import hkmu.comps380f.dao.PollQuestionRepository;
import hkmu.comps380f.model.CourseUser;
import hkmu.comps380f.service.CommentsService;
import hkmu.comps380f.service.LecturesService;
import hkmu.comps380f.service.PollCommentsService;
import java.io.IOException;
import java.security.Principal;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private LecturesService lecturesService;

    @Autowired
    private CommentsService commentsService;

    @Autowired
    private PollCommentsService pollCommentsService;

    @Resource
    CourseUserRepository couserUserRepo;

    @Resource
    PollQuestionRepository PollQuesRepo;

    @Resource
    Lecture_CommentsRepository lecture_CommentsRepository;

    @Resource
    PollCommentsRepository pollCommentsRepository;

    @GetMapping({"list"})
    public String list(ModelMap model, Principal principal) {
        model.addAttribute("lecturesDatabase", lecturesService.getLectures());
        model.addAttribute("PollQues", PollQuesRepo.findAll());
        model.addAttribute("principal", principal);
        return "list";
    }

    public static class Form {

        private String username;
        private String password;
        private String full_name;
        private int phone_number;
        private String address;

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
    }

    @GetMapping("/register")
    public ModelAndView register() {
        return new ModelAndView("register", "courseUser", new Form());
    }

    @PostMapping("/register")
    public View register(Form form) throws IOException {
        String role[] = {"ROLE_USER"};
        CourseUser user = new CourseUser(form.getUsername(),
                form.getPassword(), form.getFull_name(), form.getPhone_number(), form.getAddress(), role);
        couserUserRepo.save(user);
        return new RedirectView("/home/list", true);
    }

    @GetMapping("/comment/history")
    public String comment_material_history(ModelMap model, Principal principal, HttpServletRequest request) {
        if ((request.isUserInRole("ROLE_ADMIN"))) {
            model.addAttribute("commentdb", commentsService.getcomments());
        }
        model.addAttribute("personal_comment", lecture_CommentsRepository.readLecture_CommentsByUserName(principal.getName()));
        model.addAttribute("principal", principal);
        return "list_comment";
    }

    @GetMapping("/poll_comment/history")
    public String comment_poll_history(ModelMap model, Principal principal, HttpServletRequest request) {
        if ((request.isUserInRole("ROLE_ADMIN"))) {
            model.addAttribute("commentdb", pollCommentsService.getpoll_comments());
        }
        model.addAttribute("personal_comment", pollCommentsRepository.readPollCommentsByName(principal.getName()));
        model.addAttribute("principal", principal);
        return "list_poll_comment";
    }
}
