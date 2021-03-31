package tn.esprit.pidev.consommitounsi.entities.products;

import com.fasterxml.jackson.annotation.JsonIgnore;
import tn.esprit.pidev.consommitounsi.entities.user.User;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class ProductRating implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private double starRate;
    private long visitCount;
    private double personalRating;
    private double globalRating;
    //userrating and globalrating visitcounter
    @ManyToOne
    private User user;
    @JsonIgnore
    @ManyToOne
    private Product product;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getStarRate() {
        return starRate;
    }

    public void setStarRate(double starRate) {
        this.starRate = starRate;
    }

    public long getVisitCount() {
        return visitCount;
    }

    public void setVisitCount(long visitCount) {
        this.visitCount = visitCount;
    }

    public void incVisitCount() { this.visitCount++; }

    public double getPersonalRating() {
        return personalRating;
    }

    public void setPersonalRating(double personnalRating) {
        this.personalRating = personnalRating;
    }

    public double getGlobalRating() {
        return globalRating;
    }

    public void setGlobalRating(double globalRating) {
        this.globalRating = globalRating;
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

    public ProductRating() {
        super();
    }
}
