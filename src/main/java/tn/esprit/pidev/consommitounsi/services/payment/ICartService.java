package tn.esprit.pidev.consommitounsi.services.payment;

import tn.esprit.pidev.consommitounsi.entities.payment.Item;
import tn.esprit.pidev.consommitounsi.entities.payment.Order;
import tn.esprit.pidev.consommitounsi.models.payment.ResponseModel;

public interface ICartService {
    Order addItem(long cartId, Item item);
    Order updateItem(Item item);
    Order removeItem(Item item);
    Order confirmCart();
}