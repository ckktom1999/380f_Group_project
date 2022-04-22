package hkmu.comps380f.dao;

import hkmu.comps380f.model.Lecture_Comments;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Lecture_CommentsRepository extends JpaRepository<Lecture_Comments, Long> {

    List<Lecture_Comments> readLecture_CommentsByUserName(String userName);
}
