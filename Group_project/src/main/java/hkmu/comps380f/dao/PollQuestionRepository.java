
package hkmu.comps380f.dao;

import hkmu.comps380f.model.PollQuestion;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PollQuestionRepository extends JpaRepository<PollQuestion, Long>{
    
}
