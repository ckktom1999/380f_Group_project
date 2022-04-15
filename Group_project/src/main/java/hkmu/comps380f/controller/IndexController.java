package hkmu.comps380f.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class IndexController {

    @GetMapping
    public View index() {
        return new RedirectView("/home/list", true);
    }

    @GetMapping("/cslogin")
    public String login() {
        return "login";
    }

}
