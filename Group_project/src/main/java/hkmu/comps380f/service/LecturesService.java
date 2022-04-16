package hkmu.comps380f.service;

import hkmu.comps380f.dao.Lecture_CommentsRepository;
import hkmu.comps380f.dao.Lecture_Notes_AttachmentRepository;
import hkmu.comps380f.dao.LecturesRepository;
import hkmu.comps380f.dao.Tutorial_Notes_AttachmentRepository;
import hkmu.comps380f.exception.AttachmentNotFound;
import hkmu.comps380f.exception.LecturesNotFound;
import hkmu.comps380f.model.Lecture_Comments;
import hkmu.comps380f.model.Lecture_Notes_Attachment;
import hkmu.comps380f.model.Lectures;
import hkmu.comps380f.model.Tutorial_Notes_Attachment;
import java.io.IOException;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class LecturesService {

    @Resource
    private LecturesRepository lecturesRepo;

    @Resource
    private Lecture_Notes_AttachmentRepository lecture_notes_attachmentRepo;

    @Resource
    private Tutorial_Notes_AttachmentRepository tutorial_notes_attachmentRepo;

    @Resource
    private Lecture_CommentsRepository commentRepo;

    @Transactional
    public List<Lectures> getLectures() {
        return lecturesRepo.findAll();
    }

    @Transactional
    public Lectures getLectures(long id) {
        return lecturesRepo.findById(id).orElse(null);
    }

    @Transactional(rollbackFor = LecturesNotFound.class)
    public void delete(long id) throws LecturesNotFound {
        Lectures deletedLecture = lecturesRepo.findById(id).orElse(null);
        if (deletedLecture == null) {
            throw new LecturesNotFound();
        }
        lecturesRepo.delete(deletedLecture);
    }

    @Transactional(rollbackFor = AttachmentNotFound.class)
    public void deleteLecture_Notes_Attachment(long lecturesId, String name) throws AttachmentNotFound {
        Lectures lectures = lecturesRepo.findById(lecturesId).orElse(null);
        for (Lecture_Notes_Attachment attachment : lectures.getLecture_notes_attachments()) {
            if (attachment.getName().equals(name)) {
                lectures.delete_lecture_Attachment(attachment);
                lecturesRepo.save(lectures);
                return;
            }
        }
        throw new AttachmentNotFound();
    }

    @Transactional(rollbackFor = AttachmentNotFound.class)
    public void deleteTutorial_Notes_Attachment(long lecturesId, String name) throws AttachmentNotFound {
        Lectures lectures = lecturesRepo.findById(lecturesId).orElse(null);
        for (Tutorial_Notes_Attachment attachment : lectures.getTutorial_notes_attachments()) {
            if (attachment.getName().equals(name)) {
                lectures.delete_tutorial_Attachment(attachment);
                lecturesRepo.save(lectures);
                return;
            }
        }
        throw new AttachmentNotFound();
    }

    @Transactional
    public long createLecture(String title, String name,
            String body, List<MultipartFile> lecture_attachments, List<MultipartFile> tutorial_attachments) throws IOException {
        Lectures lectures = new Lectures();
        lectures.setTitle(title);
        Lecture_Comments comment = new Lecture_Comments();
        comment.setUserName(name);
        comment.setBody(body);
        comment.setLectures(lectures);
        for (MultipartFile filePart : lecture_attachments) {
            Lecture_Notes_Attachment attachment = new Lecture_Notes_Attachment();
            attachment.setName(filePart.getOriginalFilename());
            attachment.setMimeContentType(filePart.getContentType());
            attachment.setContents(filePart.getBytes());
            attachment.setLectures(lectures);
            if (attachment.getName() != null && attachment.getName().length() > 0
                    && attachment.getContents() != null
                    && attachment.getContents().length > 0) {
                lectures.getLecture_notes_attachments().add(attachment);
            }
        }

        for (MultipartFile filePart : tutorial_attachments) {
            Tutorial_Notes_Attachment attachment = new Tutorial_Notes_Attachment();
            attachment.setName(filePart.getOriginalFilename());
            attachment.setMimeContentType(filePart.getContentType());
            attachment.setContents(filePart.getBytes());
            attachment.setLectures(lectures);
            if (attachment.getName() != null && attachment.getName().length() > 0
                    && attachment.getContents() != null
                    && attachment.getContents().length > 0) {
                lectures.getTutorial_notes_attachments().add(attachment);
            }
        }
        Lectures savedLectures = lecturesRepo.save(lectures);
        commentRepo.save(comment);
        return savedLectures.getId();
    }

}
