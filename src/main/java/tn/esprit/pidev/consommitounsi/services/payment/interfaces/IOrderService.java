package tn.esprit.pidev.consommitounsi.services.payment.interfaces;

import tn.esprit.pidev.consommitounsi.entities.common.Address;
import tn.esprit.pidev.consommitounsi.entities.payment.Item;
import tn.esprit.pidev.consommitounsi.entities.payment.Order;
import tn.esprit.pidev.consommitounsi.entities.user.User;

import java.util.List;

public interface IOrderService {
    Order createOrder(User user, Order cart, Address shippingAddress);
    //Order getCartByUserId(Long userId);
    double calculateOrderAmount(List<Item> items);
    List<Order> getAll();
    Order getById(Long id);
    Order addOrUpdate(Order entity);
    void remove(Long id);
}
