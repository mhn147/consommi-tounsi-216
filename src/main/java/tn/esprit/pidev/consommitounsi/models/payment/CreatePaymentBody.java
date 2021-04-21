package tn.esprit.pidev.consommitounsi.models.payment;

import tn.esprit.pidev.consommitounsi.entities.payment.Item;

import java.util.List;

public class CreatePaymentBody {
    private List<Item> items;
    private String currency;

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
