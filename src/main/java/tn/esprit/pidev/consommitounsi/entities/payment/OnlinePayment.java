package tn.esprit.pidev.consommitounsi.entities.payment;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Objects;

@Entity
public class OnlinePayment extends Payment {
    private String cardNumber;
    private String firstName;
    private String lastName;

    public OnlinePayment() {
    }

    public OnlinePayment(String cardNumber, String firstName, String lastName) {
        this.cardNumber = cardNumber;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public OnlinePayment(Calendar purchaseTime, double amount, String details, String cardNumber, String firstName, String lastName) {
        super(purchaseTime, amount, details);
        this.cardNumber = cardNumber;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        OnlinePayment that = (OnlinePayment) o;
        return Objects.equals(cardNumber, that.cardNumber) &&
                Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), cardNumber, firstName, lastName);
    }
}
