package hkmu.comps380f.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class PollAnswer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String username;

    private String answer;

    @Temporal(TemporalType.TIMESTAMP)
    private Date paDate;

    @Column(insertable = false, updatable = false)
    private long pollId;

//    @ManyToOne
//    @JoinColumn(name = "poll_id")
//    private CourseUser user;
    @ManyToOne
    @JoinColumn(name = "pollId")
    private PollQuestion pQuestion;

//    public PollAnswer() {
//    }
//
//    public PollAnswer(PollAnswer pQuestion, String name, String answer) {
//        this.user = user;
//        this.pQuestion = pQuestion;
//        this.name = name;
//        this.answer = answer;
//    }
    public PollAnswer() {
    }

    public PollAnswer(String username, String answer, Date paDate,PollQuestion pQuestion) {
        this.username = username;
        this.answer = answer;
        this.paDate = paDate;
        this.pQuestion = pQuestion;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public long getPollId() {
        return pollId;
    }

    public void setPollId(long pollId) {
        this.pollId = pollId;
    }

    public PollQuestion getpQuestion() {
        return pQuestion;
    }

    public void setpQuestion(PollQuestion pQuestion) {
        this.pQuestion = pQuestion;
    }

    public Date getPaDate() {
        return paDate;
    }

    public void setPaDate(Date paDate) {
        this.paDate = paDate;
    }



}
