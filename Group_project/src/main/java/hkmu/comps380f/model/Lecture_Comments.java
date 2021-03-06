package hkmu.comps380f.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Lecture_Comments implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name")
    private String userName;
    private String body;
    private String date_time;
    @Column(name = "lectures_id", insertable = false, updatable = false)
    private long lecturesId;
    @ManyToOne
    @JoinColumn(name = "lectures_id")
    private Lectures lectures;

    // getters and setters of all properties
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDate_time() {
        return date_time.substring(0, 19);
    }

    public void setDate_time(String date_time) {
        this.date_time = date_time;
    }

    public Lectures getLectures() {
        return lectures;
    }

    public void setLectures(Lectures lectures) {
        this.lectures = lectures;
    }

    public long getLecturesId() {
        return lecturesId;
    }

    public void setLecturesId(long lecturesId) {
        this.lecturesId = lecturesId;
    }
}
