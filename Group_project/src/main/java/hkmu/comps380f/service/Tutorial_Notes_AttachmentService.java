package hkmu.comps380f.service;

import hkmu.comps380f.dao.Tutorial_Notes_AttachmentRepository;
import hkmu.comps380f.model.Tutorial_Notes_Attachment;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class Tutorial_Notes_AttachmentService {

    @Resource
    private Tutorial_Notes_AttachmentRepository attachmentRepo;

    @Transactional
    public Tutorial_Notes_Attachment getAttachment(long lecturesId, String name) {
        return attachmentRepo.findByLecturesIdAndName(lecturesId, name);
    }
}
