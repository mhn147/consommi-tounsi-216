package tn.esprit.pidev.consommitounsi.entities.products;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import tn.esprit.pidev.consommitounsi.entities.advertisements.Advertisement;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;


@Entity
@Table
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Temporal(TemporalType.DATE)
    private Calendar dueDate;
    private String name;
    private String description;
    private double price;
    private String picture;

    @ManyToMany(mappedBy = "products")
    private List<CompanyTax> taxes;

    @ManyToOne
    private Discount discount;

    @ManyToOne
    private Category category;

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Advertisement> advertisementProduct;

    public Product() {

    }

    public Product(long id, String name, String description, double price, String picture) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.picture = picture;
    }

    public Calendar getDueDate() {
        return dueDate;
    }

    public void setDueDate(Calendar dueDate) {
        this.dueDate = dueDate;
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

    public double getVatAmount() {
        throw new NotImplementedException();
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
}
