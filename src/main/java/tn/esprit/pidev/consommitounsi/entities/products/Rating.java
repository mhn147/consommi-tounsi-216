package tn.esprit.pidev.consommitounsi.entities.products;

import com.fasterxml.jackson.annotation.JsonIgnore;
import tn.esprit.pidev.consommitounsi.entities.User;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Rating implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int value;
    @ManyToOne
    private User user;
    @JsonIgnore
    @ManyToOne
    private Product product;

    public Rating() {
        super();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
