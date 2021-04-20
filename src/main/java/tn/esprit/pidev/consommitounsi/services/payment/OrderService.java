package tn.esprit.pidev.consommitounsi.services.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pidev.consommitounsi.entities.common.Address;
import tn.esprit.pidev.consommitounsi.entities.payment.Invoice;
import tn.esprit.pidev.consommitounsi.entities.payment.Item;
import tn.esprit.pidev.consommitounsi.entities.payment.Order;
import tn.esprit.pidev.consommitounsi.entities.payment.OrderStatus;
import tn.esprit.pidev.consommitounsi.entities.user.User;
import tn.esprit.pidev.consommitounsi.repositories.payment.IOrderRepository;
import tn.esprit.pidev.consommitounsi.services.payment.interfaces.IOrderService;

import java.util.List;

@Service
public class OrderService {

//    protected final IOrderRepository orderRepository;
//    protected final IItemService itemService;
//    final IInvoiceService invoiceService;
//
//    @Autowired
//    public OrderService(IOrderRepository orderRepository,
//                        IInvoiceService invoiceService,
//                        IItemService itemService) {
//        this.itemService = itemService;
//        this.orderRepository = orderRepository;
//        this.invoiceService = invoiceService;
//    }
//
//    @Override
//    public List<Order> getAll() {
//        return (List<Order>) orderRepository.findAll();
//    }
//
//    @Override
//    public Order getById(Long id) {
//        return this.orderRepository.findById(id).orElseThrow(
//                () -> new IllegalStateException("Order with id " + id + " does not exist.")
//        );
//    }
//
//    @Override
//    public Order addOrUpdate(Order order) {
//        return this.orderRepository.save(order);
//    }
//
//    @Override
//    public void remove(Long id) {
//        Order order = this.getById(id);
//        orderRepository.delete(order);
//    }
//
////    @Override
////    public Order getCartByUserId(Long userId) {
////        return this.orderRepository.getCartByUserId(userId);
////    }
//
//    @Override
//    public Order createOrder(User user, Order cart, Address shippingAddress) throws InterruptedException {
//
//        List<Item> newItems = this.itemService.addAll(cart.getItems());
//        this.emptyCart(cart);
//
//        Order newOrder = new Order(OrderStatus.CONFIRMED, newItems, user);
//        newOrder = this.orderRepository.save(newOrder);
//
//        Invoice newInvoice = this.invoiceService.createInvoice(user, newOrder, newItems, shippingAddress, false);
//
//
//        return newOrder;
//    }
//
//    @Override
//    public Order createOnlineOrder(User user, Order cart, Address shippingAddress) throws InterruptedException {
//        List<Item> newItems = this.itemService.addAll(cart.getItems());
//        this.emptyCart(cart);
//
//        Order newOrder = new Order(OrderStatus.CONFIRMED, newItems, user);
//        newOrder = this.orderRepository.save(newOrder);
//
//        Invoice newInvoice = this.invoiceService.createInvoice(user, newOrder, newItems, shippingAddress, true);
//
//        return newOrder;
//    }
//
//
//    @Override
//    public double calculateOrderAmount(List<Item> items) {
//        // Not factoring in taxes and discounts for the moment
//        // TODO
//        return 0;
//    }
//
//    @Transactional
//    protected Order emptyCart(Order cart) {
//        cart.setItems(null);
//        return this.orderRepository.save(cart);
//    }
}
