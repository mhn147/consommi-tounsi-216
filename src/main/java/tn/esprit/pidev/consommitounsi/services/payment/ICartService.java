package tn.esprit.pidev.consommitounsi.services.payment;

import tn.esprit.pidev.consommitounsi.entities.payment.Item;
import tn.esprit.pidev.consommitounsi.entities.payment.Order;

public interface ICartService {
    Order getByUserId(long userId);
    Order addItem(long cartId, Item item);
    Order removeItem(long cartId, Item item);
    boolean containsItem(long cartId, long itemId);
    boolean  itemProductExistsInCart(long cartId, long productId);
}
