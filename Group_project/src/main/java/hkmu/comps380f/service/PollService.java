package hkmu.comps380f.service;

import hkmu.comps380f.dao.PollAnswerRepository;
import hkmu.comps380f.dao.PollQuestionRepository;
import hkmu.comps380f.model.PollAnswer;
import hkmu.comps380f.model.PollQuestion;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PollService {

    @Resource
    private PollAnswerRepository pollAnsRepo;

    @Resource
    private PollQuestionRepository pollQuesRepo;

    @Transactional
    public PollQuestion getPollQuestion(long poll_id) {
        return pollQuesRepo.findById(poll_id).orElse(null);
    }

    @Transactional
    public PollAnswer getPollAnswer(long poll_id) {
        return pollAnsRepo.findById(poll_id).orElse(null);
    }
}
