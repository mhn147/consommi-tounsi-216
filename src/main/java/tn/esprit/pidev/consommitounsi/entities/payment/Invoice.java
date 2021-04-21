package tn.esprit.pidev.consommitounsi.entities.payment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import tn.esprit.pidev.consommitounsi.entities.user.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

@Entity
@Table(name = "t_invoice")
public class Invoice implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long invoiceNumber;
    @Temporal(TemporalType.DATE)
    private Calendar invoiceDate;
    @Temporal(TemporalType.DATE)
    private Calendar dueDate;
    private double totalDiscountAmount;
    private double subTotal;
    private double totalVATAmount;
    private double totalTaxesExceptVATAmount;
    private double totalTaxesAmount;
    private double total;
    @JsonIgnore
    private String invoiceFilePath;

    @OneToMany
    private List<Item> items;

    @OneToOne
    private Order order;

    @OneToOne
    private Payment payment;

    @ManyToOne
    private User user;

    public Invoice () {}

    public Invoice(Calendar invoiceDate, Calendar dueDate,
                   double totalDiscountAmount) {
        this.invoiceDate = invoiceDate;
        this.dueDate = dueDate;
        this.totalDiscountAmount = totalDiscountAmount;
    }

    public Invoice(User user, Order order, List<Item> items) {
        this.user = user;
        this.order = order;
        this.items = items;
        this.invoiceDate = Calendar.getInstance();
        this.dueDate = this.invoiceDate;
        this.dueDate.add(Calendar.DATE, 90);
        setSubTotal();
        setTaxes();
        setTotal();
        this.invoiceFilePath = "";
    }

    public String getInvoiceFilePath() {
        return invoiceFilePath;
    }

    public void setInvoiceFilePath(String invoiceFilePath) {
        this.invoiceFilePath = invoiceFilePath;
    }

    private void setSubTotal() {
        for (Item item:
             this.items) {
            this.subTotal += item.getSubTotal();
        }
    }

    private void setTaxes() {
        this.totalDiscountAmount = 0;
        for (Item item: this.items) {
            this.totalVATAmount += item.getVATAmount();
        }
        this.totalTaxesExceptVATAmount = 0;
        this.totalTaxesAmount = this.totalVATAmount;
    }

    private void setTotal() {
        this.total = this.totalTaxesAmount + this.subTotal;
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
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public double getTotalVATAmount() {
        return totalVATAmount;
    }

    public void setTotalVATAmount(double totalVATAmount) {
        this.totalVATAmount = totalVATAmount;
    }

    public double getTotalTaxesExceptVATAmount() {
        return totalTaxesExceptVATAmount;
    }

    public void setTotalTaxesExceptVATAmount(double totalTaxesExceptVATAmount) {
        this.totalTaxesExceptVATAmount = totalTaxesExceptVATAmount;
    }

    public double getTotalTaxesAmount() {
        return totalTaxesAmount;
    }

    public void setTotalTaxesAmount(double totalTaxesAmount) {
        this.totalTaxesAmount = totalTaxesAmount;
    }

    public double getTotal() {
        return total;
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