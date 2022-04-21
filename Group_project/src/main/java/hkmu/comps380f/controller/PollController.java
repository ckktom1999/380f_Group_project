package hkmu.comps380f.controller;

import hkmu.comps380f.dao.PollAnswerRepository;
import hkmu.comps380f.dao.PollQuestionRepository;
import hkmu.comps380f.model.PollAnswer;
import hkmu.comps380f.model.PollQuestion;
import hkmu.comps380f.service.PollService;
import java.io.IOException;
import java.security.Principal;
import java.util.Date;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/poll")
public class PollController {

    @Resource
    PollAnswerRepository PollAnsRepo;

    @Resource
    PollQuestionRepository PollQuesRepo;

    @Autowired
    private PollService pollService;

    public static class Form {

        private String option;

        private long pollId;

        public String getOption() {
            return option;
        }

        public void setOption(String option) {
            this.option = option;
        }

        public long getPollId() {
            return pollId;
        }

        public void setPollId(long pollId) {
            this.pollId = pollId;
        }

    }

    @GetMapping("/view/{pollId}")
    public ModelAndView showQuestionDetails(@PathVariable("pollId") long pollId, ModelMap model, @ModelAttribute("message") String message, Principal principal) {
        PollQuestion pollQues = pollService.getPollQuestion(pollId);

        long numberOfVotes = PollAnsRepo.countAnswerByPollId(pollId);
        model.addAttribute("numberOfVotes", numberOfVotes);

        if (PollAnsRepo.countByPollIdAndUsername(pollId, principal.getName()) > 0) {
            message = "You have selected " + PollAnsRepo.findAnswerByPollIdAndUsername(pollId, principal.getName()) + " at " + PollAnsRepo.findPaDateByPollIdAndUsername(pollId, principal.getName());;
        }

        //ModelAndView mav = new ModelAndView("questionDetails");
        model.addAttribute("pollQues", pollQues);
        model.addAttribute("message", message);
        return new ModelAndView("questionDetails", "addReply", new Form());
    }

    @PostMapping("/view/{pollId}")
    public ModelAndView reply(Form form, Principal principal, @PathVariable("pollId") long pollId, ModelMap model, RedirectAttributes redirectAttributes) throws IOException {

//        System.out.println(principal.getName() );
//        System.out.println(form.getOption() );     
//        PollQuestion pollQues = pollService.getPollQuestion(form.getPollId());
//        System.out.println(pollQues.getQuestion() );
//        System.out.println(pollId);
        //System.out.println("Count User Name:            "+PollAnsRepo.countByUsername(principal.getName()));
        //if (PollAnsRepo.countByUsername(principal.getName()) == 0) {
        String message = "";

        if (PollAnsRepo.countByPollIdAndUsername(form.getPollId(), principal.getName()) == 0) {
            PollAnswer Answer = new PollAnswer(principal.getName(), form.getOption(), new Date(), pollService.getPollQuestion(form.getPollId()));
            PollAnsRepo.save(Answer);
            message = "You have selected " + form.getOption() + " at " + PollAnsRepo.findPaDateByPollIdAndUsername(form.getPollId(), principal.getName());
        } else if (PollAnsRepo.findAnswerByPollIdAndUsername(form.getPollId(), principal.getName()).equals(form.getOption()) == false) {
            PollAnsRepo.updateByUsername(form.getOption(), new Date(), principal.getName(), form.getPollId());
            message = "You have edited your option" + " to \"" + form.getOption() + "\" at " + PollAnsRepo.findPaDateByPollIdAndUsername(form.getPollId(), principal.getName());
        } else {
            message = "You have selected " + PollAnsRepo.findAnswerByPollIdAndUsername(form.getPollId(), principal.getName()) + " at " + PollAnsRepo.findPaDateByPollIdAndUsername(form.getPollId(), principal.getName());
        }

        System.out.println(message);
        redirectAttributes.addAttribute("message", message);
        //model.put("message", message);
        //PollAnswer pollAns = pollService.getPollAnswer(pollId);
        //model.addAttribute("PollQues", PollQuesRepo.findAll());
        return new ModelAndView("redirect:/poll/view/" + pollId);
    }

}
