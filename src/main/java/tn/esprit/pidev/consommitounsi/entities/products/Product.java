package tn.esprit.pidev.consommitounsi.entities.products;

import tn.esprit.pidev.consommitounsi.entities.advertisements.Advertisement;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
@Table
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String description;
    private double price;
    private String picture;
    private double vatamount;

    @ManyToOne
    private Category category;

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Advertisement> advertisementproduct;

    public Product() {

    }

    public Product(long id, String name, String description, double price, String picture, double vatamount ) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.picture = picture;
        this.vatamount = vatamount;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public double getVatamount() {
        return vatamount;
    }

    public void setVatamount(double vatamount) {
        this.vatamount = vatamount;
    }


}
