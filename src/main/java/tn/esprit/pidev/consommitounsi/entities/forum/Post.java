package tn.esprit.pidev.consommitounsi.entities.forum;

import com.fasterxml.jackson.annotation.JsonIgnore;
import tn.esprit.pidev.consommitounsi.entities.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
public class Post implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String content;
    private String medias;
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @JsonIgnore
    @ManyToOne
    private Topic topic;
    @ManyToOne
    private User user;
    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    private List<PostLike> likes;

    public Post() {
        super();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMedias() {
        return medias;
    }

    public void setMedias(String medias) {
        this.medias = medias;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<PostLike> getLikes() {
        return likes;
    }

    public void setLikes(List<PostLike> likes) {
        this.likes = likes;
    }
}
