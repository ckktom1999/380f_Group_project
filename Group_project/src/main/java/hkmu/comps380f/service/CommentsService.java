package hkmu.comps380f.service;

import hkmu.comps380f.dao.Lecture_CommentsRepository;
import hkmu.comps380f.model.Lecture_Comments;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommentsService {

    @Resource
    private Lecture_CommentsRepository commentRepo;

    @Transactional
    public List<Lecture_Comments> getcomments() {
        return commentRepo.findAll();
    }

}
