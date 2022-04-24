package hkmu.comps380f.controller;

import hkmu.comps380f.dao.PollAnswerRepository;
import hkmu.comps380f.dao.PollCommentsRepository;
import hkmu.comps380f.dao.PollQuestionRepository;
import hkmu.comps380f.exception.CommentNotFound;
import hkmu.comps380f.model.PollAnswer;
import hkmu.comps380f.model.PollComments;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/poll")
public class PollController {

    @Resource
    PollAnswerRepository PollAnsRepo;

    @Resource
    PollQuestionRepository PollQuesRepo;

    @Resource
    PollCommentsRepository PollCommRepo;

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
        //PollComments pollComm = pollService.getPollComments(pollId);

        long optionACount = PollAnsRepo.countOptionAdByPollId(pollId);
        long optionBCount = PollAnsRepo.countOptionBdByPollId(pollId);
        long optionCCount = PollAnsRepo.countOptionCdByPollId(pollId);
        long optionDCount = PollAnsRepo.countOptionDdByPollId(pollId);
        long numberOfVotes = PollAnsRepo.countAnswerByPollId(pollId);
        model.addAttribute("numberOfVotes", numberOfVotes);
        model.addAttribute("optionACount", optionACount);
        model.addAttribute("optionBCount", optionBCount);
        model.addAttribute("optionCCount", optionCCount);
        model.addAttribute("optionDCount", optionDCount);
        if (PollAnsRepo.countByPollIdAndUsername(pollId, principal.getName()) > 0) {
            message = "You have selected " + PollAnsRepo.findAnswerByPollIdAndUsername(pollId, principal.getName()) + " at " + PollAnsRepo.findPaDateByPollIdAndUsername(pollId, principal.getName());;
        }

        //ModelAndView mav = new ModelAndView("questionDetails");
        model.addAttribute("pollQues", pollQues);
        //model.addAttribute("pollComm", PollCommRepo.findAllByPollId(pollId));
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
        if (form.getOption() != null) {

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
        } else {
            return new ModelAndView("redirect:/poll/view/" + pollId);
        }
        System.out.println(message);
        redirectAttributes.addAttribute("message", message);
        //model.put("message", message);
        //PollAnswer pollAns = pollService.getPollAnswer(pollId);
        //model.addAttribute("PollQues", PollQuesRepo.findAll());
        return new ModelAndView("redirect:/poll/view/" + pollId);
    }

    @GetMapping("/delete/{pollId}")
    public ModelAndView delete(@PathVariable("pollId") long pollId, ModelMap model, @ModelAttribute("message") String message, Principal principal) {
        PollQuesRepo.deleteByPollId(pollId);

        return new ModelAndView("redirect:/home/list");
    }

    public static class savePollForm {

        private String question;

        private String optionA;

        private String optionB;

        private String optionC;

        private String optionD;

        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }

        public String getOptionA() {
            return optionA;
        }

        public void setOptionA(String optionA) {
            this.optionA = optionA;
        }

        public String getOptionB() {
            return optionB;
        }

        public void setOptionB(String optionB) {
            this.optionB = optionB;
        }

        public String getOptionC() {
            return optionC;
        }

        public void setOptionC(String optionC) {
            this.optionC = optionC;
        }

        public String getOptionD() {
            return optionD;
        }

        public void setOptionD(String optionD) {
            this.optionD = optionD;
        }

    }

    @GetMapping("/create")
    public ModelAndView createPoll(ModelMap model, Principal principal) {

        return new ModelAndView("addPoll", "createPoll", new savePollForm());
    }

    @PostMapping("/create")
    public String savePollQuestion(savePollForm form, Principal principal, ModelMap model) {
        PollQuestion PollQues = new PollQuestion(form.getQuestion(), form.getOptionA(), form.getOptionB(), form.getOptionC(), form.getOptionD(), principal.getName(), new Date());
        PollQuesRepo.save(PollQues);
        return "redirect:/home/list";
    }

    @PostMapping("/createPollComment")
    public String savePollComent(@RequestParam(value = "cpComment", required = false) String cpComment, @RequestParam(value = "pollId", required = false) long pollId, Principal principal, ModelMap model) {

        PollComments PollComm = new PollComments(principal.getName(), cpComment, new Date(), pollService.getPollQuestion(pollId));
        PollCommRepo.save(PollComm);

        return "redirect:/poll/view/" + pollId;
    }

    @GetMapping("/deleteComment/{commentId}/{pollId}")
    public ModelAndView deleteComment(@PathVariable("commentId") long commentId, @PathVariable("pollId") long pollId) throws CommentNotFound {
        pollService.delete_comment(commentId, pollId);

        return new ModelAndView("redirect:/poll/view/" + pollId);
    }

}
