package tn.esprit.pidev.consommitounsi.entities.payment;

import javax.persistence.Entity;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Objects;

@Entity
public class AtDeliveryPayment extends Payment{
    private String paymentDocument;

    public AtDeliveryPayment() {
    }

    public AtDeliveryPayment(String paymentDocument) {
        this.paymentDocument = paymentDocument;
    }

    public AtDeliveryPayment(Calendar purchaseTime, double amount, String details, String paymentDocument) {
        super(purchaseTime, amount, details);
        this.paymentDocument = paymentDocument;
    }

    public String getPaymentDocument() {
        return paymentDocument;
    }

    public void setPaymentDocument(String paymentDocument) {
        this.paymentDocument = paymentDocument;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        AtDeliveryPayment that = (AtDeliveryPayment) o;
        return Objects.equals(paymentDocument, that.paymentDocument);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), paymentDocument);
    }
}
