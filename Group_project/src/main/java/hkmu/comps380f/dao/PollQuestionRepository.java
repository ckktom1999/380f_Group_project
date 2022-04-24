
package hkmu.comps380f.dao;

import hkmu.comps380f.model.PollQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;


public interface PollQuestionRepository extends JpaRepository<PollQuestion, Long>{
    
    @Transactional
    void deleteByPollId(long pollId);
}
