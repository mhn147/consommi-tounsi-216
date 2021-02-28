package tn.esprit.pidev.consommitounsi.entities.payment;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Temporal(TemporalType.TIME)
    private LocalDate purchaseTime;
    private double amount;
    private String details;

    public Payment() {}

    public Payment(LocalDate purchaseTime, double amount, String details) {
        this.purchaseTime = purchaseTime;
        this.amount = amount;
        this.details = details;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getPurchaseTime() {
        return purchaseTime;
    }

    public void setPurchaseTime(LocalDate purchaseTime) {
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
