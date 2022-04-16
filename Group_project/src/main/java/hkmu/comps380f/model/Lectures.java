package hkmu.comps380f.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
public class Lectures implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lectures_id")
    private long id;
    private String title;
    @OneToMany(mappedBy = "lectures", fetch = FetchType.EAGER,
            cascade = CascadeType.ALL, orphanRemoval = true)
    @Fetch(FetchMode.SUBSELECT)
    private List<Lecture_Notes_Attachment> lecture_notes_attachments = new ArrayList<>();
    @OneToMany(mappedBy = "lectures", fetch = FetchType.EAGER,
            cascade = CascadeType.ALL, orphanRemoval = true)
    @Fetch(FetchMode.SUBSELECT)
    private List<Tutorial_Notes_Attachment> tutorial_notes_attachments = new ArrayList<>();
    @OneToMany(mappedBy = "lectures", fetch = FetchType.EAGER,
            cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Lecture_Comments> lecture_comments = new ArrayList<>();

    // getters and setters of all properties
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Lecture_Notes_Attachment> getLecture_notes_attachments() {
        return lecture_notes_attachments;
    }

    public void setLecture_notes_attachments(List<Lecture_Notes_Attachment> lecture_notes_attachments) {
        this.lecture_notes_attachments = lecture_notes_attachments;
    }

    public List<Tutorial_Notes_Attachment> getTutorial_notes_attachments() {
        return tutorial_notes_attachments;
    }

    public void setTutorial_notes_attachments(List<Tutorial_Notes_Attachment> tutorial_notes_attachments) {
        this.tutorial_notes_attachments = tutorial_notes_attachments;
    }

    public List<Lecture_Comments> getLecture_comments() {
        return lecture_comments;
    }

    public void setLecture_comments(List<Lecture_Comments> lecture_comments) {
        this.lecture_comments = lecture_comments;
    }

    public void delete_lecture_Attachment(Lecture_Notes_Attachment attachment) {
        attachment.setLectures(null);
        this.lecture_notes_attachments.remove(attachment);
    }

    public void delete_tutorial_Attachment(Tutorial_Notes_Attachment attachment) {
        attachment.setLectures(null);
        this.tutorial_notes_attachments.remove(attachment);
    }

    public void delete_lecture_Comments(Lecture_Comments comment) {
        comment.setLectures(null);
        this.lecture_comments.remove(comment);
    }
}
