package tn.esprit.pidev.consommitounsi.models.payment;

import tn.esprit.pidev.consommitounsi.entities.common.Address;
import tn.esprit.pidev.consommitounsi.entities.payment.Item;

import java.util.List;

public class PaymentModel {
    private long userId;
    private Address shippingAddress;

    public PaymentModel() {}


    public PaymentModel(long userId, Address shippingAddress) {
        this.userId = userId;
        this.shippingAddress = shippingAddress;
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
