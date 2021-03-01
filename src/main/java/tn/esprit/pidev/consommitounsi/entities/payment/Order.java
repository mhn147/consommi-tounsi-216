package tn.esprit.pidev.consommitounsi.entities.payment;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

//@Entity
public class Order implements Serializable {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private OrderStatus status;

    public Order() {}


    public Order(OrderStatus orderStatus) {
        this.status = orderStatus;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id && status == order.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status);
    }
}
