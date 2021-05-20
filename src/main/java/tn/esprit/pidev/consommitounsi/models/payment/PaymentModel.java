package tn.esprit.pidev.consommitounsi.models.payment;

import tn.esprit.pidev.consommitounsi.entities.common.Address;
import tn.esprit.pidev.consommitounsi.entities.payment.PaymentType;

public class PaymentModel {

    private long userId;
    private Address shippingAddress;
    private PaymentType paymentType;

    public PaymentModel() {}


    public PaymentModel(long userId, Address shippingAddress) {
        this.userId = userId;
        this.shippingAddress = shippingAddress;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }
}
