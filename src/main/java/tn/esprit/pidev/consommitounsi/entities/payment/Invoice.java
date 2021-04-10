package tn.esprit.pidev.consommitounsi.entities.payment;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import tn.esprit.pidev.consommitounsi.entities.user.User;
import tn.esprit.pidev.consommitounsi.entities.products.Product;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

@Entity
public class Invoice implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long invoiceNumber;
    @Temporal(TemporalType.DATE)
    private Calendar invoiceDate;
    @Temporal(TemporalType.DATE)
    private Calendar dueDate;
    private double subTotal;
    private double totalDiscountAmount;
    private double totalVATAmount;
    private double total;


    @ManyToOne
    private User user;
    @OneToOne
    private Order order;
    @OneToOne
    private Payment payment;
    @OneToMany
    private List<Item> items;

    public Invoice () {}

    public Invoice(Calendar invoiceDate, Calendar dueDate,
                   double totalDiscountAmount) {
        this.invoiceDate = invoiceDate;
        this.dueDate = dueDate;
        this.totalDiscountAmount = totalDiscountAmount;
    }

    public long getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(long invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public Calendar getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Calendar invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public Calendar getDueDate() {
        return dueDate;
    }

    public void setDueDate(Calendar dueDate) {
        this.dueDate = dueDate;
    }

    public double getTotalDiscountAmount() {
        return totalDiscountAmount;
    }

    public void setTotalDiscountAmount(double totalDiscountAmount) {
        this.totalDiscountAmount = totalDiscountAmount;
    }

    public double getSubTotal() {
        throw new NotImplementedException();
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public double getTotalVATAmount() {
       throw new NotImplementedException();
    }

    public void setTotalVATAmount(double totalVATAmount) {
        this.totalVATAmount = totalVATAmount;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}