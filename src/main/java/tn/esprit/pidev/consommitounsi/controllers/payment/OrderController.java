package tn.esprit.pidev.consommitounsi.controllers.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidev.consommitounsi.entities.payment.Item;
import tn.esprit.pidev.consommitounsi.entities.payment.Order;
import tn.esprit.pidev.consommitounsi.services.payment.IOrderService;
import tn.esprit.pidev.consommitounsi.services.payment.ItemService;

import java.util.List;

@RestController
@RequestMapping(path = "orders")
public class OrderController {

    private final IOrderService orderService;

    @Autowired
    public OrderController(IOrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<Order> getOrders() {
        return this.orderService.getAll();
    }

    @GetMapping(path = "{orderId}")
    public Order get(@PathVariable("orderId") Long orderId) {
        return this.orderService.getById(orderId);
    }

    @PostMapping
    public Order add(@RequestBody Order order) {
        return this.orderService.add(order);
    }

    @DeleteMapping(path = "{orderId}")
    public void remove(@PathVariable("orderId") Long orderId) {
        this.orderService.remove(orderId);
    }

    @PutMapping(path = "{orderId}")
    public void update(@PathVariable("orderId") Long orderId,
                       @RequestBody Order order) {
        this.orderService.update(order, orderId);
    }
}
