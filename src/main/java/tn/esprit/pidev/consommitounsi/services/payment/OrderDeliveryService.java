package tn.esprit.pidev.consommitounsi.services.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pidev.consommitounsi.entities.payment.Order;
import tn.esprit.pidev.consommitounsi.entities.payment.OrderDelivery;
import tn.esprit.pidev.consommitounsi.repositories.payment.IOrderDeliveryRepository;
import tn.esprit.pidev.consommitounsi.services.common.IService;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class OrderDeliveryService implements IOrderDeliveryService, IService<OrderDelivery> {

    private final IOrderDeliveryRepository orderDeliveryRepository;

    @Autowired
    public OrderDeliveryService(IOrderDeliveryRepository orderDeliveryRepository) {
        this.orderDeliveryRepository = orderDeliveryRepository;
    }

    @Override
    public List<OrderDelivery> getAll() {
        return (List<OrderDelivery>)orderDeliveryRepository.findAll();
    }

    @Override
    public OrderDelivery getById(Long id) {
        return this.orderDeliveryRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("Order Delivery with id " + id + " does not exist.")
        );
    }

    @Override
    public OrderDelivery addOrUpdate(OrderDelivery orderDelivery) {
        return this.orderDeliveryRepository.save(orderDelivery);
    }

    @Override
    public void remove(Long id) {
        OrderDelivery orderDelivery = this.getById(id);
        orderDeliveryRepository.delete(orderDelivery);
    }
}
