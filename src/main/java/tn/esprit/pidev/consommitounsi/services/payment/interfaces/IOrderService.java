package tn.esprit.pidev.consommitounsi.services.payment;

import tn.esprit.pidev.consommitounsi.entities.common.Address;
import tn.esprit.pidev.consommitounsi.entities.payment.Item;
import tn.esprit.pidev.consommitounsi.entities.payment.Order;
import tn.esprit.pidev.consommitounsi.entities.user.User;
import tn.esprit.pidev.consommitounsi.models.payment.PaymentModel;
import tn.esprit.pidev.consommitounsi.models.payment.ResponseModel;
import tn.esprit.pidev.consommitounsi.services.common.IService;

import java.util.List;

public interface IOrderService extends IService<Order> {
    Order createOrder(User user, Order cart, Address shippingAddress) throws InterruptedException;
    Order createOnlineOrder(User user, Order cart, Address shippingAddress) throws InterruptedException;
    Order getCartByUserId(Long userId);
    double calculateOrderAmount(List<Item> items);
}
