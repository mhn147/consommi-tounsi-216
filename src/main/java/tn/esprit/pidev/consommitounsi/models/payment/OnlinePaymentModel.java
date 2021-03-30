package tn.esprit.pidev.consommitounsi.models.payment;

import tn.esprit.pidev.consommitounsi.entities.common.Address;
import tn.esprit.pidev.consommitounsi.entities.payment.Item;

import java.util.List;

public class OnlinePaymentModel extends PaymentModel {
    private double amount;
    private String token;

    public OnlinePaymentModel(String token, double amount) {
        this.token = token;
        this.amount = amount;
    }

    public OnlinePaymentModel(double amount, long userId, Address shippingAddress, String token) {
        super(userId, shippingAddress);
        this.token = token;
        this.amount = amount;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
