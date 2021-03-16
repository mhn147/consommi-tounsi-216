package tn.esprit.pidev.consommitounsi.entities.events;


import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Cagnotte implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int jackpot;
    @OneToMany
    private List<Ticket> tickets;

    public Cagnotte() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getJackpot() {
        return jackpot;
    }

    public void setJackpot(int jackpot) {
        this.jackpot = jackpot;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public Cagnotte(long id, int jackpot) {
        this.id = id;
        this.jackpot = jackpot;
    }

}
