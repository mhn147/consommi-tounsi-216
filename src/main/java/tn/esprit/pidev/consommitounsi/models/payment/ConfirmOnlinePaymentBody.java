package tn.esprit.pidev.consommitounsi.models.payment;

import tn.esprit.pidev.consommitounsi.entities.common.Address;

public class ConfirmOnlinePaymentBody {
    private long userId;
    private double amount;
    private Address shippingAddress;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }
}
