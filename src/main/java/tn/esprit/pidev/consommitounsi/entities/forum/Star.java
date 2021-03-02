package tn.esprit.pidev.consommitounsi.entities.forum;

import com.fasterxml.jackson.annotation.JsonIgnore;
import tn.esprit.pidev.consommitounsi.entities.User;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Star implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int value;
    @ManyToOne
    private User user;
    @JsonIgnore
    @ManyToOne
    private Topic topic;

    public Star() {
        super();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }
}
