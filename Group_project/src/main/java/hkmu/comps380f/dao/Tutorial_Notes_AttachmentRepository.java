package hkmu.comps380f.dao;

import hkmu.comps380f.model.Tutorial_Notes_Attachment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Tutorial_Notes_AttachmentRepository extends JpaRepository<Tutorial_Notes_Attachment, Long> {
    public Tutorial_Notes_Attachment findByLecturesIdAndName(long lecturesId, String name);
}
