package tn.esprit.pidev.consommitounsi.entities.payment;

import tn.esprit.pidev.consommitounsi.entities.common.Address;

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

    @OneToMany
    private List<Address> deliveriesAddresses;

    @OneToMany
    private List<Order> orders;

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

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDelivery that = (OrderDelivery) o;
        return id == that.id && durationInHours == that.durationInHours;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, durationInHours);
    }
}
