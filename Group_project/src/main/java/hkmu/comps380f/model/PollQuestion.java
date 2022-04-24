package hkmu.comps380f.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
public class PollQuestion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long pollId;

    private String question;

    private String optionA;

    private String optionB;

    private String optionC;

    private String optionD;

    private String username;

    @Temporal(TemporalType.TIMESTAMP)
    private Date pqDate;

    @OneToMany(mappedBy = "pQuestion", fetch = FetchType.EAGER,
            cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PollAnswer> answer = new ArrayList<>();

    @OneToMany(mappedBy = "pQuestion", fetch = FetchType.EAGER,
            cascade = CascadeType.ALL, orphanRemoval = true)
    @Fetch(FetchMode.SUBSELECT)
    private List<PollComments> pComment = new ArrayList<>();

    public PollQuestion() {
    }

    public PollQuestion(String question, String optionA, String optionB, String optionC, String optionD, String username, Date pqDate) {
        this.question = question;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.username = username;
        this.pqDate = pqDate;
    }

    public long getPollId() {
        return pollId;
    }

    public void setPollId(long pollId) {
        this.pollId = pollId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOptionA() {
        return optionA;
    }

    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public void setOptionC(String optionC) {
        this.optionC = optionC;
    }

    public String getOptionD() {
        return optionD;
    }

    public void setOptionD(String optionD) {
        this.optionD = optionD;
    }

    public Date getPqDate() {
        return pqDate;
    }

    public void setPqDate(Date pqDate) {
        this.pqDate = pqDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<PollAnswer> getAnswer() {
        return answer;
    }

    public void setAnswer(List<PollAnswer> answer) {
        this.answer = answer;
    }

    public List<PollComments> getpComment() {
        return pComment;
    }

    public void setpComment(List<PollComments> pComment) {
        this.pComment = pComment;
    }

    public void delete_pollComments(PollComments comment) {
        comment.setpQuestion(null);
        this.pComment.remove(comment);
    }

}
