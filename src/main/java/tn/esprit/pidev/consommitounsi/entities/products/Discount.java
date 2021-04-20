package tn.esprit.pidev.consommitounsi.entities.products;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;

@Entity
public class Discount implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String description;
    @Temporal(TemporalType.DATE)
    private Calendar startsAt;
    @Temporal(TemporalType.DATE)
    private Calendar endsAt;
    private double discountValue;
    private ValueNature discountType;

    public Discount() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Calendar getStartsAt() {
        return startsAt;
    }

    public void setStartsAt(Calendar startsAt) {
        this.startsAt = startsAt;
    }

    public Calendar getEndsAt() {
        return endsAt;
    }

    public void setEndsAt(Calendar endsAt) {
        this.endsAt = endsAt;
    }

    public double getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(double discountValue) {
        this.discountValue = discountValue;
    }

    public ValueNature getDiscountType() {
        return discountType;
    }

    public void setDiscountType(ValueNature discountType) {
        this.discountType = discountType;
    }
}
