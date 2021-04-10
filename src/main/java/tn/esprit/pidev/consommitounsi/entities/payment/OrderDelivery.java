package tn.esprit.pidev.consommitounsi.entities.payment;

import tn.esprit.pidev.consommitounsi.entities.common.Address;
import tn.esprit.pidev.consommitounsi.entities.user.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "t_order_delivery")
public class OrderDelivery implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Temporal(TemporalType.DATE)
    private Calendar startsAt;
    @Temporal(TemporalType.DATE)
    private Calendar endsAt;

    @OneToOne
    private Invoice invoice;
    @OneToOne
    private Address address;
    @OneToOne
    private User deliverer;


    public OrderDelivery() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public User getDeliverer() {
        return deliverer;
    }

    public void setDeliverer(User deliverer) {
        this.deliverer = deliverer;
    }

    public Calendar getStartsAt() {
        return startsAt;
    }

    public void setStartsAt(Calendar startsAt) {
        this.startsAt = startsAt;
    }

    public Calendar getEndsAt() {
        return endsAt;
    }

    public void setEndsAt(Calendar endsAt) {
        this.endsAt = endsAt;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }
}
