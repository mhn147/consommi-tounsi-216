package tn.esprit.pidev.consommitounsi.entities.payment;

import tn.esprit.pidev.consommitounsi.entities.products.Product;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "t_item")
public class Item implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int quantity;
    private double discountRate;
    private double discountAmount;
    private double subTotal;
    private double VATRate;
    private double VATAmount;
    private double taxesExceptVATAmount;
    private double totalTaxesAmount;

    @ManyToOne
    private Product product;

    public Item() {}

    public Item(int quantity, double discountRate, double discountAmount, double subTotal,
                double VATRate, double VATAmount, double taxesExceptVATAmount, double totalTaxesAmount) {
        this.quantity = quantity;
        this.discountRate = discountRate;
        this.discountAmount = discountAmount;
        this.subTotal = subTotal;
        this.VATRate = VATRate;
        this.VATAmount = VATAmount;
        this.taxesExceptVATAmount = taxesExceptVATAmount;
        this.totalTaxesAmount = totalTaxesAmount;
    }

    public Item(int quantity, Product product) {
        this(quantity, 0, 0, product.getPrice(),
                0.1, product.getPrice() * 0.1,
                0, product.getPrice() * 0.1);
        this.product = product;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(double discountRate) {
        this.discountRate = discountRate;
    }

    public double getDiscountAmount() {
        // TODO
        return discountAmount;
    }

    public void setDiscountAmount(double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public double getSubTotal() {
        return this.product.getPrice() * this.quantity;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public double getVATRate() {
        return VATRate;
    }

    public void setVATRate(double VATRate) {
        this.VATRate = VATRate;
    }

    public double getVATAmount() {
        // TODO
        return VATAmount;
    }

    public void setVATAmount(double VATAmount) {
        this.VATAmount = VATAmount;
    }

    public double getTaxesExceptVATAmount() {
        // TODO
        return taxesExceptVATAmount;
    }

    public void setTaxesExceptVATAmount(double taxesExceptVATAmount) {
        this.taxesExceptVATAmount = taxesExceptVATAmount;
    }

    public double getTotalTaxesAmount() {
        // TODO
        return totalTaxesAmount;
    }

    public void setTotalTaxesAmount(double totalTaxesAmount) {
        // TODO
        this.totalTaxesAmount = totalTaxesAmount;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return id == item.id && quantity == item.quantity &&
                Double.compare(item.discountRate, discountRate) == 0 &&
                Double.compare(item.discountAmount, discountAmount) == 0 &&
                Double.compare(item.subTotal, subTotal) == 0 &&
                Double.compare(item.VATRate, VATRate) == 0 &&
                Double.compare(item.VATAmount, VATAmount) == 0 &&
                Double.compare(item.taxesExceptVATAmount, taxesExceptVATAmount) == 0 &&
                Double.compare(item.totalTaxesAmount, totalTaxesAmount) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, quantity, discountRate, discountAmount,
                subTotal, VATRate, VATAmount, taxesExceptVATAmount, totalTaxesAmount);
    }
}
