package hkmu.comps380f.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/home")
public class HomeController  {

    @GetMapping({"list"})
    public String list(ModelMap model) {
        return "list";
    }
     
}
