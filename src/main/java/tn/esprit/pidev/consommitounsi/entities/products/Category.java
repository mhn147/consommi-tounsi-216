package tn.esprit.pidev.consommitounsi.entities.products;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity

public class Category implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;
    private String name;

    @OneToMany(cascade = { CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy = "category")
    private List<Product> Products;

    @ManyToOne
    private Category baseCategory;

    @OneToMany(cascade = { CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy = "baseCategory")
    private List<Category> subCategories;


    public List<Product> getProducts() {
        return Products;
    }

    public void setProducts(List<Product> products) {
        Products = products;
    }

    public Category getBaseCategory() {
        return baseCategory;
    }

    public void setBaseCategory(Category baseCategory) {
        this.baseCategory = baseCategory;
    }

    public List<Category> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(List<Category> subCategories) {
        this.subCategories = subCategories;
    }

    public Category(){

    }

    public Category(long id, String name, List<Product> products) {
        Id = id;
        this.name = name;
        Products = products;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
