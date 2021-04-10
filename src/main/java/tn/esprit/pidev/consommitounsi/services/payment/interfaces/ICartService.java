package tn.esprit.pidev.consommitounsi.services.payment.interfaces;

import tn.esprit.pidev.consommitounsi.entities.common.Address;
import tn.esprit.pidev.consommitounsi.entities.payment.Cart;
import tn.esprit.pidev.consommitounsi.entities.payment.Item;
import tn.esprit.pidev.consommitounsi.entities.payment.Order;
import tn.esprit.pidev.consommitounsi.entities.user.User;

public interface ICartService {
    Cart createCart(User user);
    Cart getByUserId(long userId);
    Cart addItem(long cartId, Item item);
    Item updateItemQuantity(Item item, int quantity);
    Cart removeItem(long cartId, Item item);
    Cart empty(long cartId);
    boolean containsItem(long cartId, long itemId);
    boolean itemProductExistsInCart(long cartId, long productId);
}
