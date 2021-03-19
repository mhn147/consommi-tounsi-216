package tn.esprit.pidev.consommitounsi.entities.advertisements;

import tn.esprit.pidev.consommitounsi.entities.user.User;
import tn.esprit.pidev.consommitounsi.entities.products.Product;

import javax.persistence.*;
import java.io.Serializable;
import java.time.DateTimeException;
import java.util.Date;
import java.util.List;

@Entity
public class Advertisement implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private  String name;
    private Date startingDate;
    private Date endingDate;
    private long targetViewCount ;
    private long finalViewsCount;
    private double cost;
    private String type;


    @ManyToMany
    private List<User> users;

    @ManyToMany(mappedBy="advertisementproduct", cascade = { CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Product> products;

    public Advertisement(){

    }
    public Advertisement(long id, String name, Date startingDate, Date endingDate, long targetViewCount, long finalViewsCount, double cost, String type) {
        this.id = id;
        this.name = name;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
        this.targetViewCount = targetViewCount;
        this.finalViewsCount = finalViewsCount;
        this.cost = cost;
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(Date startingDate) {
        this.startingDate = startingDate;
    }

    public Date getEndingDate() {
        return endingDate;
    }

    public void setEndingDate(Date endingDate) {
        this.endingDate = endingDate;
    }

    public long getTargetViewCount() {
        return targetViewCount;
    }

    public void setTargetViewCount(long targetViewCount) {
        this.targetViewCount = targetViewCount;
    }

    public long getFinalViewsCount() {
        return finalViewsCount;
    }

    public void setFinalViewsCount(long finalViewsCount) {
        this.finalViewsCount = finalViewsCount;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
