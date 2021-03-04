package tn.esprit.pidev.consommitounsi.entities.products;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity

public class CompanyTax implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private double taxValue;
    private TaxType taxType;

    @ManyToMany
    private List<Product> products;

    public CompanyTax() {

    }

    public CompanyTax(String name, double taxvalue, TaxType taxType) {
        this.name = name;
        this.taxValue = taxvalue;
        this.taxType = taxType;
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

    public double getTaxValue() {
        return taxValue;
    }

    public void setTaxValue(double taxvalue) {
        this.taxValue = taxvalue;
    }

    public TaxType getTaxType() {
        return taxType;
    }

    public void setTaxType(TaxType taxType) {
        this.taxType = taxType;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompanyTax that = (CompanyTax) o;
        return id == that.id && Double.compare(that.taxValue, taxValue) == 0 && Objects.equals(name, that.name) && taxType == that.taxType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, taxValue, taxType);
    }
}
