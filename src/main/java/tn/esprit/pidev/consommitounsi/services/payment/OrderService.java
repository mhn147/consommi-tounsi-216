package tn.esprit.pidev.consommitounsi.services.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pidev.consommitounsi.entities.common.Address;
import tn.esprit.pidev.consommitounsi.entities.payment.Item;
import tn.esprit.pidev.consommitounsi.entities.payment.Order;
import tn.esprit.pidev.consommitounsi.entities.payment.OrderStatus;
import tn.esprit.pidev.consommitounsi.entities.user.User;
import tn.esprit.pidev.consommitounsi.repositories.payment.IOrderRepository;
import tn.esprit.pidev.consommitounsi.services.payment.interfaces.IOrderService;

import java.util.List;

@Service
public class OrderService implements IOrderService {

    protected final IOrderRepository orderRepository;

    @Autowired
    public OrderService(IOrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> getAll() {
        return (List<Order>) orderRepository.findAll();
    }

    @Override
    public Order getById(Long id) {
        return this.orderRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("Order with id " + id + " does not exist.")
        );
    }

    @Override
    public Order addOrUpdate(Order order) {
        return this.orderRepository.save(order);
    }

    @Override
    public void remove(Long id) {
        Order order = this.getById(id);
        orderRepository.delete(order);
    }

//    @Override
//    public Order getCartByUserId(Long userId) {
//        return this.orderRepository.getCartByUserId(userId);
//    }

    @Override
    public Order createOrder(User user, Order cart, Address shippingAddress) {
        this.emptyCart(cart);

        Order newOrder = new Order(OrderStatus.PENDING, cart.getItems(), user);
        return this.orderRepository.save(newOrder);
    }

    @Override
    public double calculateOrderAmount(List<Item> items) {
        // Not factoring in taxes and discounts for the moment
        // TODO
        return 0;
    }

    private Order emptyCart(Order cart) {
        cart.setItems(null);
        return this.orderRepository.save(cart);
    }
}
