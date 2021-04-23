package tn.esprit.pidev.consommitounsi.services.payment.interfaces;

import tn.esprit.pidev.consommitounsi.entities.payment.OrderDelivery;

import java.util.List;

public interface IOrderDeliveryService {
    List<OrderDelivery> getAll();
    OrderDelivery getById(Long id);
    OrderDelivery addOrUpdate(OrderDelivery entity);
    void remove(Long id);
}
