package tn.esprit.pidev.consommitounsi.entities.products;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.stereotype.Indexed;
import tn.esprit.pidev.consommitounsi.entities.user.User;
import tn.esprit.pidev.consommitounsi.entities.advertisements.Advertisement;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;


@Entity
@Indexed
@Table
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Temporal(TemporalType.DATE)
    private Calendar creationDate;
    private String name;
    private String description;

    //@Field(analyze = Analyze.NO) // You already have something like this, make sure to set analyze = NO
    //@Facet(forField = "price", encoding = FacetEncodingType.DOUBLE) // You need to add this
    private double price;
    private String picture;

    @ManyToMany(mappedBy = "products")
    private List<CompanyTax> taxes;

    @ManyToOne
    private Discount discount;

    @ManyToOne
    private Category category;

    @JsonIgnore
    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Advertisement> advertisementProduct;


    @ManyToOne
    private User user;
    @OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE)
    private List<Comment> comments;
    @OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE)
    private List<ProductRating> ratings;




    public Product() {
        super();

    }

    public Product(long id, String name, String description, double price, String picture) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.picture = picture;
    }

    public Calendar getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Calendar dueDate) {
        this.creationDate = dueDate;
    }

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
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

    public List<CompanyTax> getTaxes() {
        return taxes;
    }

    public void setTaxes(List<CompanyTax> taxes) {
        this.taxes = taxes;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Advertisement> getAdvertisementProduct() {
        return advertisementProduct;
    }

    public void setAdvertisementProduct(List<Advertisement> advertisementProduct) {
        this.advertisementProduct = advertisementProduct;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<ProductRating> getRatings() {
        return ratings;
    }

    public void setRatings(List<ProductRating> ratings) {
        this.ratings = ratings;
}}
