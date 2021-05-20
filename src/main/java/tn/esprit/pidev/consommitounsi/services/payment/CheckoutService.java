package tn.esprit.pidev.consommitounsi.services.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pidev.consommitounsi.entities.payment.*;
import tn.esprit.pidev.consommitounsi.entities.user.User;
import tn.esprit.pidev.consommitounsi.models.payment.PaymentModel;
import tn.esprit.pidev.consommitounsi.services.payment.interfaces.*;
import tn.esprit.pidev.consommitounsi.services.payment.interfaces.IInvoiceService;
import tn.esprit.pidev.consommitounsi.services.user.IUserService;

import java.util.Calendar;
import java.util.List;

@Service
public class CheckoutService implements ICheckoutService {

    private final IUserService userService;
    private final IOrderService orderService;
    private final ICartService cartService;
    private final IInvoiceService invoiceService;
    private final IPaymentService paymentService;
    private final ITaxesService taxesService;

    @Autowired
    public CheckoutService(IUserService userService,
                           IOrderService orderService,
                           ICartService cartService,
                           IInvoiceService invoiceService,
                           IPaymentService paymentService,
                           ITaxesService taxesService) {
        this.userService = userService;
        this.orderService = orderService;
        this.cartService = cartService;
        this.invoiceService = invoiceService;
        this.paymentService = paymentService;
        this.taxesService = taxesService;
    }

    @Override
    public Order createOnlineOrder(PaymentModel paymentModel) {
        User user = this.userService.getById(paymentModel.getUserId());
        Cart cart = this.cartService.getByUserId(user.getId());
        List<Item> items = cart.getItems();
        cart.setItems(null);
        Order order = _createOrder(user, items);
        this.orderService.addOrUpdate(order);
        order.setStatus(OrderStatus.CONFIRMED);
        Invoice invoice;
        try {
            invoice = this.invoiceService.createInvoice(user, order, paymentModel.getShippingAddress(), true);
            Payment payment = _createPayment(invoice, order.getItems(), PaymentType.ONLINE);
            this.paymentService.addOrUpdate(payment);
        } catch (Exception ex) {
            System.err.println(ex.getStackTrace());
        }

        return order;
    }

    @Override
    public Order createAtDeliveryOrder(PaymentModel paymentModel) {
        User user = this.userService.getById(paymentModel.getUserId());
        Cart cart = this.cartService.getByUserId(user.getId());
        List<Item> items = cart.getItems();
        cart.setItems(null);
        Order order = _createOrder(user, items);
        this.orderService.addOrUpdate(order);

        return order;
    }



    private Order _createOrder(User user, List<Item> items) {
        Order order = new Order();
        order.setUser(user);
        order.setCreationDate(Calendar.getInstance());
        order.setItems(items);
        return order;
    }



    private Payment _createPayment(Invoice invoice, List<Item> items, PaymentType paymentType) {
        Payment payment = new Payment();
        payment.setAmount(this.taxesService.calculateTaxes(items));
        payment.setPaymentType(paymentType);
        payment.setInvoice(invoice);
        payment.setDetails("Payment For: invoice #" + invoice.getInvoiceNumber());
        payment.setPurchaseTime(Calendar.getInstance());
        return payment;
    }
}
