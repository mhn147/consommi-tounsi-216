package tn.esprit.pidev.consommitounsi.services.payment.interfaces;

import tn.esprit.pidev.consommitounsi.entities.payment.Item;

import java.util.List;

public interface ITaxesService {
    double calculateTaxes(List<Item> items);
    double calculateSubTotal(List<Item> items);
}
