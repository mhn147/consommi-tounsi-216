package tn.esprit.pidev.consommitounsi.entities.events;

import tn.esprit.pidev.consommitounsi.entities.User;

import javax.persistence.*;
import java.io.Serializable;


@Entity
public class Donation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int amount;
    @ManyToOne
    private Event ev;

    @ManyToOne
    private User user;

    public Donation() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Event getEv() {
        return ev;
    }

    public void setEv(Event ev) {
        this.ev = ev;
    }

    public Donation(long id, int amount) {
        this.id = id;
        this.amount = amount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
