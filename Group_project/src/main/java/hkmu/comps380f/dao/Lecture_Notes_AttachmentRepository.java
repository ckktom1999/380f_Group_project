package hkmu.comps380f.dao;

import hkmu.comps380f.model.Lecture_Notes_Attachment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Lecture_Notes_AttachmentRepository extends JpaRepository<Lecture_Notes_Attachment, Long> {
    public Lecture_Notes_Attachment findByLecturesIdAndName(long lecturesId, String name);
}
