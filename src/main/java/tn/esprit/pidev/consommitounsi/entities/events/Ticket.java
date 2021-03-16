package tn.esprit.pidev.consommitounsi.entities.events;

import tn.esprit.pidev.consommitounsi.entities.user.User;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Ticket implements Serializable {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private long id;
     private int bets;
     @ManyToOne
     private Cagnotte cagnotte;
     @ManyToOne
     private User user;

    public Ticket() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getBets() {
        return bets;
    }

    public void setBets(int bets) {
        this.bets = bets;
    }

    public Cagnotte getCagnotte() {
        return cagnotte;
    }

    public void setCagnotte(Cagnotte cagnotte) {
        this.cagnotte = cagnotte;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Ticket(long id, int bets) {
        this.id = id;
        this.bets = bets;
    }
}
