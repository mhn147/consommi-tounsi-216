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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
    private List<Product> Products;

    @ManyToOne
    private Category category;

    @OneToMany(cascade = { CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy = "category")
    private List<Category> subCategories;


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
