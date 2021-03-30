package tn.esprit.pidev.consommitounsi.controllers.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidev.consommitounsi.entities.payment.Order;
import tn.esprit.pidev.consommitounsi.entities.payment.Payment;
import tn.esprit.pidev.consommitounsi.entities.user.User;
import tn.esprit.pidev.consommitounsi.models.payment.PaymentModel;
import tn.esprit.pidev.consommitounsi.models.payment.ResponseModel;
import tn.esprit.pidev.consommitounsi.services.payment.IOrderService;
import tn.esprit.pidev.consommitounsi.services.payment.IPaymentService;
import tn.esprit.pidev.consommitounsi.services.user.IUserService;
import tn.esprit.pidev.consommitounsi.utils.payment.ResponseBuilder;
import tn.esprit.pidev.consommitounsi.utils.payment.Validators;

import java.util.List;

@RestController
@RequestMapping
public class PaymentController {

    private final IPaymentService paymentService;
    private final IUserService userService;
    private final IOrderService orderService;
    private final Validators validators;
    private final ResponseBuilder responseBuilder;

    @Autowired
    public PaymentController(IPaymentService paymentService,
                             IUserService userService,
                             IOrderService orderService,
                             Validators validators,
                             ResponseBuilder responseBuilder) {
        this.paymentService = paymentService;
        this.userService = userService;
        this.orderService = orderService;
        this.validators = validators;
        this.responseBuilder = responseBuilder;
    }

    @PostMapping(path = "payments/at-delivery")
    public ResponseEntity<ResponseModel<Order>> addAtDeliveryPayment(@RequestBody PaymentModel payment)
    {
        User user = this.userService.getById(payment.getUserId());

        if (user == null)
        {
            return this.responseBuilder.buildResponse(HttpStatus.BAD_REQUEST, "", "User does not exist", null);
        }

        Order cart = this.orderService.getCartByUserId(payment.getUserId());

        if (cart == null) {
            return this.responseBuilder.buildResponse(HttpStatus.NOT_FOUND, "",
                    "User doesn't have a cart", null);
        }

        Order newOrder = this.orderService.createOrder(user, cart, payment.getShippingAddress());

        return this.responseBuilder.buildResponse(HttpStatus.CREATED, "Order created with success.", "", newOrder);
    }

    @PostMapping(path = "payments/online")
    public ResponseModel<Payment> addOnlinePayment()
    {
        return null;
    }


    @GetMapping(path = "payments")
    public List<Payment> getPayments() {
        return this.paymentService.getAll();
    }

    @GetMapping(path = "payments/{paymentId}")
    public Payment get(@PathVariable("paymentId") Long paymentId) {
        return this.paymentService.getById(paymentId);
    }

    @PostMapping(path = "payments")
    public Payment add(@RequestBody Payment payment) {
        return this.paymentService.addOrUpdate(payment);
    }

    @DeleteMapping(path = "payments/{paymentId}")
    public void remove(@PathVariable("paymentId") Long paymentId) {
        this.paymentService.remove(paymentId);
    }

    @PutMapping(path = "/payments{paymentId}")
    public void update(@PathVariable("paymentId") Long paymentId,
                       @RequestBody Payment payment) {
        payment.setId(paymentId);
        this.paymentService.addOrUpdate(payment);
    }
}
