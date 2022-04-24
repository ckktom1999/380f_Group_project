package hkmu.comps380f.service;

import hkmu.comps380f.dao.PollCommentsRepository;
import hkmu.comps380f.model.PollComments;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PollCommentsService {

    @Resource
    private PollCommentsRepository pollRepo;

    @Transactional
    public List<PollComments> getpoll_comments() {
        return pollRepo.findAll();
    }

}
