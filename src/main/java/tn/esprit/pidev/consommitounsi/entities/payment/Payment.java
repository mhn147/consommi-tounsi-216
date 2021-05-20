package tn.esprit.pidev.consommitounsi.entities.payment;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Objects;

@Entity
@Table(name = "t_payment")
public class Payment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;
    @Temporal(TemporalType.DATE)
    private Calendar purchaseTime;
    private double amount;
    private String details;

    @OneToOne(mappedBy = "payment")
    private Invoice invoice;

    public Payment() {}

    public Payment(Calendar purchaseTime, double amount, String details) {
        this.purchaseTime = purchaseTime;
        this.amount = amount;
        this.details = details;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Calendar getPurchaseTime() {
        return purchaseTime;
    }

    public void setPurchaseTime(Calendar purchaseTime) {
        this.purchaseTime = purchaseTime;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return id == payment.id && Double.compare(payment.amount, amount) == 0
                && Objects.equals(purchaseTime, payment.purchaseTime) &&
                Objects.equals(details, payment.details);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, purchaseTime, amount, details);
    }
}
