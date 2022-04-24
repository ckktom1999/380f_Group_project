package hkmu.comps380f.dao;

import hkmu.comps380f.model.PollComments;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PollCommentsRepository extends JpaRepository<PollComments, Long> {

//    @Modifying(clearAutomatically = true)
//    @Transactional
//    @Query("delete from pollComments pc where pc.id = ?1")
//    void deleteById(long commentId);
    List<PollComments> readPollCommentsByName(String Name);
}
