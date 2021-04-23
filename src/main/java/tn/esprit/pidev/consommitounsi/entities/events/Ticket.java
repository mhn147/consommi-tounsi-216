package tn.esprit.pidev.consommitounsi.entities.events;

import com.fasterxml.jackson.annotation.JsonIgnore;
import tn.esprit.pidev.consommitounsi.entities.user.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Ticket implements Serializable {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private long id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date TicketDate;
    @JsonIgnore
     @ManyToOne
     private Cagnotte cagnotte;
    @JsonIgnore
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

    public Date getTicketDate() {
        return TicketDate;
    }

    public void setTicketDate(Date ticketDate) {
        TicketDate = ticketDate;
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

    public Ticket(long id, Date ticketDate) {
        this.id = id;
        TicketDate = ticketDate;
    }
}
