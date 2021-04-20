package tn.esprit.pidev.consommitounsi.controllers.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidev.consommitounsi.entities.payment.Order;
import tn.esprit.pidev.consommitounsi.services.payment.interfaces.IOrderService;

import java.util.List;

@RestController
@RequestMapping
public class OrderController {

    private final IOrderService orderService;

    @Autowired
    public OrderController(IOrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping(path = "orders")
    public List<Order> getOrders() {
        return this.orderService.getAll();
    }

    @GetMapping(path = "orders/{orderId}")
    public Order get(@PathVariable("orderId") Long orderId) {
        return this.orderService.getById(orderId);
    }

    @PostMapping(path = "orders")
    public Order add(@RequestBody Order order) {
        return this.orderService.addOrUpdate(order);
    }

    @DeleteMapping(path = "orders/{orderId}")
    public void remove(@PathVariable("orderId") Long orderId) {
        this.orderService.remove(orderId);
    }

    @PutMapping(path = "orders/{orderId}")
    public void update(@PathVariable("orderId") Long orderId,
                       @RequestBody Order order) {
        order.setId(orderId);
        this.orderService.addOrUpdate(order);
    }
}
