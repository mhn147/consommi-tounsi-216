package tn.esprit.pidev.consommitounsi.entities.payment;

import tn.esprit.pidev.consommitounsi.entities.common.Address;
import tn.esprit.pidev.consommitounsi.entities.user.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "t_order_delivery")
public class OrderDelivery implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int durationInHours;
    private double deliveryCost;
    @Transient
    private String deliveryNoteDocument;

    @OneToMany
    private List<Address> deliveriesAddresses;

    @OneToOne
    private Order order;

    @OneToOne
    private User deliverer;

    @ManyToOne
    private Address address;

    public OrderDelivery() {}

    public OrderDelivery(int durationInHours) {
        this.durationInHours = durationInHours;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getDurationInHours() {
        return durationInHours;
    }

    public void setDurationInHours(int durationInHours) {
        this.durationInHours = durationInHours;
    }

    public List<Address> getDeliveriesAddresses() {
        return deliveriesAddresses;
    }

    public void setDeliveriesAddresses(List<Address> deliveriesAddresses) {
        this.deliveriesAddresses = deliveriesAddresses;
    }

    public double getDeliveryCost() {
        return deliveryCost;
    }

    public void setDeliveryCost(double deliveryCost) {
        this.deliveryCost = deliveryCost;
    }

    public String getDeliveryNoteDocument() {
        // TODO
        return deliveryNoteDocument;
    }

    public void setDeliveryNoteDocument(String deliveryNoteDocument) {
        this.deliveryNoteDocument = deliveryNoteDocument;
    }

    public Order getOrders() {
        return order;
    }

    public void setOrders(Order order) {
        this.order = order;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
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
}
