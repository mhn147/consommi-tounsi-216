package tn.esprit.pidev.consommitounsi.services.payment.interfaces;

import tn.esprit.pidev.consommitounsi.entities.common.Address;
import tn.esprit.pidev.consommitounsi.entities.payment.Item;
import tn.esprit.pidev.consommitounsi.entities.payment.Order;
import tn.esprit.pidev.consommitounsi.entities.user.User;

import java.util.List;

public interface IOrderService {
    List<Order> getAll();
    Order getById(long orderId);
    Order addOrUpdate(Order order);
    void delete(long orderId);
}
