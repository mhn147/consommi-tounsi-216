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
import tn.esprit.pidev.consommitounsi.services.payment.interfaces.IInvoiceService;

import java.util.List;

@Service
public class OrderService implements IOrderService{

    private final IOrderRepository orderRepository;
    private final IInvoiceService invoiceService;

    @Autowired
    public OrderService(IOrderRepository orderRepository,
                        IInvoiceService invoiceService) {
        this.orderRepository = orderRepository;
        this.invoiceService = invoiceService;
    }

    @Override
    public List<Order> getAll() {
        return (List<Order>) orderRepository.findAll();
    }

    @Override
    public Order getById(long orderId) {
        return this.orderRepository.findById(orderId).orElse(null);
    }

    @Override
    public Order addOrUpdate(Order order) {
        return this.orderRepository.save(order);
    }

    @Override
    public void delete(long orderId) {
        Order order = this.getById(orderId);
        if (order != null) {
            this.orderRepository.delete(order);
        }
    }
}
