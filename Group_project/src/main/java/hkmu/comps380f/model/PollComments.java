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
public class PollComments implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String body;

    @Temporal(TemporalType.TIMESTAMP)
    private Date pcDate;

    @Column(insertable = false, updatable = false)
    private long pollId;

    @ManyToOne
    @JoinColumn(name = "pollId")
    private PollQuestion pQuestion;

    public PollComments() {

    }

    public PollComments(String name, String body, Date pcDate, PollQuestion pQuestion) {
        this.name = name;
        this.body = body;
        this.pcDate = pcDate;
        this.pQuestion = pQuestion;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getPcDate() {
        return pcDate;
    }

    public void setPcDate(Date pcDate) {
        this.pcDate = pcDate;
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

}
