package tn.esprit.pidev.consommitounsi.services.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pidev.consommitounsi.entities.payment.Order;
import tn.esprit.pidev.consommitounsi.repositories.payment.IOrderRepository;
import tn.esprit.pidev.consommitounsi.services.common.IService;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class OrderService implements IOrderService, IService<Order> {

    private final IOrderRepository orderRepository;

    @Autowired
    public OrderService(IOrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> getAll() {
        return (List<Order>)orderRepository.findAll();
    }

    @Override
    public Order getById(Long id) {
        return this.orderRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("Order with id " + id + " does not exist.")
        );
    }

    @Override
    public Order add(Order order) {
        return this.orderRepository.save(order);
    }

    @Override
    @Transactional
    public Order update(Order order, Long id) {
        Order oldOrder = this.getById(id);

        // TODO Validation
        oldOrder = order;

        return order;
    }

    @Override
    public void remove(Long id) {
        Order order = this.getById(id);
        orderRepository.delete(order);
    }
}
