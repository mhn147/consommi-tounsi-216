package tn.esprit.pidev.consommitounsi.entities.events;

import tn.esprit.pidev.consommitounsi.entities.user.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Participation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date ParticipDate;
    @ManyToOne
    private Event ev;
    @ManyToOne
    private User user;

    public Participation() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getParticipDate() {
        return ParticipDate;
    }

    public void setParticipDate(Date participDate) {
        ParticipDate = participDate;
    }

    public Event getEv() {
        return ev;
    }

    public void setEv(Event ev) {
        this.ev = ev;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Participation(long id, Date participDate) {
        this.id = id;
        ParticipDate = participDate;
    }
}
