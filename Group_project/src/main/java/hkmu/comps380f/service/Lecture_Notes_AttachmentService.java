package hkmu.comps380f.service;

import hkmu.comps380f.dao.Lecture_Notes_AttachmentRepository;
import hkmu.comps380f.model.Lecture_Notes_Attachment;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class Lecture_Notes_AttachmentService {

    @Resource
    private Lecture_Notes_AttachmentRepository attachmentRepo;

    @Transactional
    public Lecture_Notes_Attachment getAttachment(long lecturesId, String name) {
        return attachmentRepo.findByLecturesIdAndName(lecturesId, name);
    }

}
