package hkmu.comps380f.dao;

import hkmu.comps380f.model.PollAnswer;
import java.util.Date;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface PollAnswerRepository extends JpaRepository<PollAnswer, Long> {

    long countAnswerByPollId(long pollId);

    @Transactional
    @Query("select count(*) from PollAnswer pa where pa.pollId = ?1 AND pa.username= ?2")
    long countByPollIdAndUsername(long pollId, String username);

    @Transactional
    @Query("select pa.paDate from PollAnswer pa where pa.pollId = ?1 AND pa.username= ?2")
    Date findPaDateByPollIdAndUsername(long pollId, String username);

//    String findAnswerByPollId(long pollId);

    @Transactional
    @Query("select pa.answer from PollAnswer pa where pa.pollId = ?1 AND pa.username= ?2")
    String findAnswerByPollIdAndUsername(long pollId, String username);

    @Transactional
    @Query("select count(*) from PollAnswer pa where pa.pollId = ?1 AND pa.answer= 'A'")
    long countOptionAdByPollId(long pollId);

    @Transactional
    @Query("select count(*) from PollAnswer pa where pa.pollId = ?1 AND pa.answer= 'B'")
    long countOptionBdByPollId(long pollId);

    @Transactional
    @Query("select count(*) from PollAnswer pa where pa.pollId = ?1 AND pa.answer= 'C'")
    long countOptionCdByPollId(long pollId);

    @Transactional
    @Query("select count(*) from PollAnswer pa where pa.pollId = ?1 AND pa.answer= 'D'")
    long countOptionDdByPollId(long pollId);


//    @Transactional
//    @Query("select count(pa.*) from PollAnswer pa where pa.pollId = ?1")
//    long countAnswer(long pollId);
    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("update PollAnswer pa set pa.answer = ?1, pa.paDate = ?2 where pa.username = ?3 and pa.pollId= ?4")
    void updateByUsername(String answer, Date paDate, String username, long pollId);
}
