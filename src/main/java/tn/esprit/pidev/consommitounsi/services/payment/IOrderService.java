package tn.esprit.pidev.consommitounsi.services.payment;

import tn.esprit.pidev.consommitounsi.entities.payment.Order;
import tn.esprit.pidev.consommitounsi.models.payment.ResponseModel;
import tn.esprit.pidev.consommitounsi.services.common.IService;

public interface IOrderService extends IService<Order> {
    Order getCartByUserId(Long userId);
}
