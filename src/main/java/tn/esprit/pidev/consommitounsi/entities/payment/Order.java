package tn.esprit.pidev.consommitounsi.entities.payment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import tn.esprit.pidev.consommitounsi.entities.user.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "t_order")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Temporal(TemporalType.DATE)
    private Calendar creationDate;
    private OrderStatus status;
    @ManyToOne
    @JsonIgnore
    public User user;
    @OneToMany
    private List<Item> items;

    public Order() {
    }

    public Order(OrderStatus orderStatus) {
        this.status = orderStatus;
    }

    public Order(OrderStatus orderStatus, List<Item> items) {
        this(orderStatus);
        this.items = items;
    }

    public Order(OrderStatus orderStatus, List<Item> items, User user) {
        this(orderStatus, items);
        this.user = user;
    }

    public Calendar getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Calendar creationDate) {
        this.creationDate = creationDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Enumerated(EnumType.STRING)
    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
