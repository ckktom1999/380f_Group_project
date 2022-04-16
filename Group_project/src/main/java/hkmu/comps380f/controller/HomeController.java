package hkmu.comps380f.controller;

import hkmu.comps380f.service.Lecture_Notes_AttachmentService;
import hkmu.comps380f.service.LecturesService;
import hkmu.comps380f.service.Tutorial_Notes_AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private LecturesService lecturesService;

    @Autowired
    private Lecture_Notes_AttachmentService lecture_notes_attachmentService;

    @Autowired
    private Tutorial_Notes_AttachmentService tutorial_notes_attachmentService;

    @GetMapping({"list"})
    public String list(ModelMap model) {
        model.addAttribute("lecturesDatabase", lecturesService.getLectures());
        return "list";
    }

}
