package hkmu.comps380f.controller;

import hkmu.comps380f.exception.LecturesNotFound;
import hkmu.comps380f.model.Lecture_Notes_Attachment;
import hkmu.comps380f.model.Lectures;
import hkmu.comps380f.model.Tutorial_Notes_Attachment;
import hkmu.comps380f.service.Lecture_Notes_AttachmentService;
import hkmu.comps380f.service.LecturesService;
import hkmu.comps380f.service.Tutorial_Notes_AttachmentService;
import hkmu.comps380f.view.DownloadingView;
import java.io.IOException;
import java.security.Principal;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/material")
public class MaterialController {

    @Autowired
    private LecturesService lecturesService;

    @Autowired
    private Lecture_Notes_AttachmentService lecture_notes_attachmentService;

    @Autowired
    private Tutorial_Notes_AttachmentService tutorial_notes_attachmentService;

    @GetMapping("/create")
    public ModelAndView create() {
        return new ModelAndView("addlecture", "lectureForm", new Form());
    }

    public static class Form {

        private String title;
        private String comment;
        private List<MultipartFile> lecture_notes_attachments;
        private List<MultipartFile> tutorial_notes_attachments;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<MultipartFile> getLecture_notes_attachments() {
            return lecture_notes_attachments;
        }

        public void setLecture_notes_attachments(List<MultipartFile> lecture_notes_attachments) {
            this.lecture_notes_attachments = lecture_notes_attachments;
        }

        public List<MultipartFile> getTutorial_notes_attachments() {
            return tutorial_notes_attachments;
        }

        public void setTutorial_notes_attachments(List<MultipartFile> tutorial_notes_attachments) {
            this.tutorial_notes_attachments = tutorial_notes_attachments;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }
    }

    @PostMapping("/create")
    public String create(Form form, Principal principal) throws IOException {
        long lectureId = lecturesService.createLecture(form.getTitle(),
                principal.getName(), form.getComment(), form.getLecture_notes_attachments(), form.getTutorial_notes_attachments());
        return "redirect:/material/view/" + lectureId;
    }

    @GetMapping("/view/{lectureid}")
    public ModelAndView view(@PathVariable("lectureid") long lectureid) {
        Lectures lecture = lecturesService.getLectures(lectureid);
        if (lecture == null) {
            return new ModelAndView(new RedirectView("/home/list", true));
        }
        ModelAndView modelAndView = new ModelAndView("view_material");
        modelAndView.addObject("lecture", lecture);
        Form commentForm = new Form();
        modelAndView.addObject("commentForm", commentForm);
        return modelAndView;
    }

    @PostMapping("/view/{lectureid}")
    public String addcomment(@PathVariable("lectureid") long lectureid, Form form,
            Principal principal, HttpServletRequest request)
            throws IOException, LecturesNotFound {
        Lectures lecture = lecturesService.getLectures(lectureid);
        if (lecture == null) {
            return "redirect:/home/list";
        }
        lecturesService.add_comment(lecture, principal.getName(),
                form.getComment());
        return "redirect:/material/view/" + lectureid;
    }

    @GetMapping("/{lectureid}/tutorial_notes_attachments/{attachment:.+}")
    public View download_tutorial_note(@PathVariable("lectureid") long lectureid,
            @PathVariable("attachment") String name) {
        Tutorial_Notes_Attachment attachment = tutorial_notes_attachmentService.getAttachment(lectureid, name);
        if (attachment != null) {
            return new DownloadingView(attachment.getName(),
                    attachment.getMimeContentType(), attachment.getContents());
        }
        return new RedirectView("/home/list", true);
    }

    @GetMapping("/{lectureid}/lecture_notes_attachments/{attachment:.+}")
    public View download(@PathVariable("lectureid") long lectureid,
            @PathVariable("attachment") String name) {
        Lecture_Notes_Attachment attachment = lecture_notes_attachmentService.getAttachment(lectureid, name);
        if (attachment != null) {
            return new DownloadingView(attachment.getName(),
                    attachment.getMimeContentType(), attachment.getContents());
        }
        return new RedirectView("/home/list", true);
    }

    @GetMapping("/delete/{lectureid}")
    public String deleteLecture(@PathVariable("lectureid") long lectureid)
            throws LecturesNotFound {
        lecturesService.delete(lectureid);
        return "redirect:/home/list";
    }
}
