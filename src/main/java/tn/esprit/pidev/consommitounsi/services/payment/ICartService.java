package tn.esprit.pidev.consommitounsi.services.payment;

import tn.esprit.pidev.consommitounsi.entities.payment.Item;
import tn.esprit.pidev.consommitounsi.entities.payment.Order;
import tn.esprit.pidev.consommitounsi.entities.products.Product;
import tn.esprit.pidev.consommitounsi.models.payment.ResponseModel;

public interface ICartService {
    Order getByUserId(long userId);
    Order addItem(long cartId, Item item);
    Order removeItem(long cartId, Item item);
    boolean containsItem(long cartId, long itemId);
    Order confirmCart();
    boolean  itemProductExistsInCart(long cartId, long productId);
}
