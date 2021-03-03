package tn.esprit.pidev.consommitounsi.entities.forum;

import com.fasterxml.jackson.annotation.JsonIgnore;
import tn.esprit.pidev.consommitounsi.entities.User;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class PostLike implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private boolean liked;
    @ManyToOne
    private User user;
    @JsonIgnore
    @ManyToOne
    private Post post;

    public PostLike() {
        super();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
