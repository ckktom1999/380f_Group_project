package hkmu.comps380f.service;

import hkmu.comps380f.dao.PollAnswerRepository;
import hkmu.comps380f.dao.PollCommentsRepository;
import hkmu.comps380f.dao.PollQuestionRepository;
import hkmu.comps380f.exception.CommentNotFound;
import hkmu.comps380f.model.PollAnswer;
import hkmu.comps380f.model.PollComments;
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

    @Resource
    private PollCommentsRepository pollCommRepo;

    @Transactional
    public PollQuestion getPollQuestion(long poll_id) {
        return pollQuesRepo.findById(poll_id).orElse(null);
    }

    @Transactional
    public PollAnswer getPollAnswer(long poll_id) {
        return pollAnsRepo.findById(poll_id).orElse(null);
    }

    @Transactional
    public PollComments getPollComments(long poll_id) {
        return pollCommRepo.findById(poll_id).orElse(null);
    }

    @Transactional(rollbackFor = CommentNotFound.class)
    public void delete_comment(long commentId, long pollId) throws CommentNotFound {
        PollQuestion PollQues = pollQuesRepo.findById(pollId).orElse(null);
        for (PollComments comment : PollQues.getpComment()) {
            long comment_id = comment.getId();
            if (comment_id == commentId) {
                PollQues.delete_pollComments(comment);
                pollQuesRepo.save(PollQues);
                return;
            }
        }
    }

}
