package tn.esprit.pidev.consommitounsi.entities.payment;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long invoiceNumber;
    @Temporal(TemporalType.DATE)
    private LocalDate invoiceDate;
    @Temporal(TemporalType.DATE)
    private LocalDate dueDate;
    private double totalDiscountAmount;
    @Transient
    private double subTotal;
    private double totalVATAmount;
    private double totalTaxesExceptVATAmount;
    private double totalTaxesAmount;
    @Transient
    private double total;

    public Invoice () {}

    public Invoice(LocalDate invoiceDate, LocalDate dueDate,
                   double totalDiscountAmount, double subTotal, double totalVATAmount,
                   double totalTaxesExceptVATAmount, double totalTaxesAmount) {
        this.invoiceDate = invoiceDate;
        this.dueDate = dueDate;
        this.totalDiscountAmount = totalDiscountAmount;
        this.subTotal = subTotal;
        this.totalVATAmount = totalVATAmount;
        this.totalTaxesExceptVATAmount = totalTaxesExceptVATAmount;
        this.totalTaxesAmount = totalTaxesAmount;
    }

    public long getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(long invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public LocalDate getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(LocalDate invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public double getTotalDiscountAmount() {
        return totalDiscountAmount;
    }

    public void setTotalDiscountAmount(double totalDiscountAmount) {
        this.totalDiscountAmount = totalDiscountAmount;
    }

    public double getSubTotal() {
        // !!! TODO
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
        // !!! TODO
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invoice invoice = (Invoice) o;
        return invoiceNumber == invoice.invoiceNumber &&
                Double.compare(invoice.totalDiscountAmount, totalDiscountAmount) == 0 &&
                Double.compare(invoice.subTotal, subTotal) == 0 &&
                Double.compare(invoice.totalVATAmount, totalVATAmount) == 0 &&
                Double.compare(invoice.totalTaxesExceptVATAmount, totalTaxesExceptVATAmount) == 0 &&
                Double.compare(invoice.totalTaxesAmount, totalTaxesAmount) == 0 &&
                Objects.equals(invoiceDate, invoice.invoiceDate) &&
                Objects.equals(dueDate, invoice.dueDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(invoiceNumber, invoiceDate, dueDate, totalDiscountAmount,
                subTotal, totalVATAmount, totalTaxesExceptVATAmount, totalTaxesAmount);
    }
}