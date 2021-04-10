package tn.esprit.pidev.consommitounsi.controllers.payment;

import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidev.consommitounsi.entities.payment.Order;
import tn.esprit.pidev.consommitounsi.entities.payment.Payment;
import tn.esprit.pidev.consommitounsi.entities.user.User;
import tn.esprit.pidev.consommitounsi.models.payment.PaymentModel;
import tn.esprit.pidev.consommitounsi.models.payment.ResponseModel;
import tn.esprit.pidev.consommitounsi.models.payment.online.ConfirmOnlinePaymentBody;
import tn.esprit.pidev.consommitounsi.models.payment.online.CreatePaymentBody;
import tn.esprit.pidev.consommitounsi.models.payment.online.CreatePaymentResponse;
import tn.esprit.pidev.consommitounsi.services.payment.interfaces.IOrderService;
import tn.esprit.pidev.consommitounsi.services.payment.interfaces.IPaymentService;
import tn.esprit.pidev.consommitounsi.services.user.IUserService;
import tn.esprit.pidev.consommitounsi.utils.payment.ResponseBuilder;
import tn.esprit.pidev.consommitounsi.utils.payment.Validators;

import java.util.List;

@RestController
@RequestMapping
public class PaymentController {
//    private final IPaymentService paymentService;
//    private final IUserService userService;
//    private final IOrderService orderService;
//    private final Validators validators;
//    private final ResponseBuilder responseBuilder;
//
//    @Autowired
//    public PaymentController(IPaymentService paymentService,
//                             IUserService userService,
//                             IOrderService orderService,
//                             Validators validators,
//                             ResponseBuilder responseBuilder) {
//        this.paymentService = paymentService;
//        this.userService = userService;
//        this.orderService = orderService;
//        this.validators = validators;
//        this.responseBuilder = responseBuilder;
//    }
//
//    @PostMapping(path = "payments/at-delivery")
//    public ResponseEntity<ResponseModel<Order>> addAtDeliveryPayment(@RequestBody PaymentModel payment)
//    {
//        User user = this.userService.getById(payment.getUserId());
//
//        if (user == null)
//        {
//            return this.responseBuilder.buildResponse(HttpStatus.BAD_REQUEST, "", "User does not exist", null);
//        }
//
//        Order cart = this.orderService.getCartByUserId(payment.getUserId());
//
//        if (cart == null) {
//            return this.responseBuilder.buildResponse(HttpStatus.NOT_FOUND, "",
//                    "User doesn't have a cart", null);
//        }
//
//        Order newOrder = this.orderService.createOrder(user, cart, payment.getShippingAddress());
//
//        return this.responseBuilder.buildResponse(HttpStatus.CREATED, "Order created with success.", "", newOrder);
//    }
//
//    @PostMapping(path = "payments/online/create-intent")
//    public CreatePaymentResponse createOnlinePaymentIntent(
//            @RequestBody CreatePaymentBody createPaymentBody)
//
//    {
//        PaymentIntentCreateParams createParams = new PaymentIntentCreateParams.Builder()
//                    .setCurrency(createPaymentBody.getCurrency())
//                    .setAmount((long)this.orderService.calculateOrderAmount(createPaymentBody.getItems()))
//                    .build();
//
//        // Create a PaymentIntent with the order amount and currency
//        try {
//            PaymentIntent intent = PaymentIntent.create(createParams);
//            // Send publishable key and PaymentIntent details to client
//            // TODO MOVE PUBLIC KEY TO ENV VAR
//            return new CreatePaymentResponse("pk_test_51IZDC9Dnznjyl503ZfnPyTPrlzW3kNOMmYEjJogLySOGrokf1zpQHmarcyPiYl6h7PwnbnCpeuKSq1bcG8MKpj3W00RD7OpTDl", intent.getClientSecret());
//        } catch (StripeException ex) {
//            return null;
//        }
//    }
//
//    @PostMapping(path = "payments/online/confirmCardPayment")
//    public ResponseEntity<ResponseModel<Order>> confirmOnlinePayment(
//            @RequestBody ConfirmOnlinePaymentBody confirmOnlinePaymentBody)
//
//    {
//        User user = this.userService.getById(confirmOnlinePaymentBody.getUserId());
//
//        if (user == null)
//        {
//            return this.responseBuilder.buildResponse(HttpStatus.BAD_REQUEST, "", "User does not exist", null);
//        }
//
//        Order cart = this.orderService.getCartByUserId(confirmOnlinePaymentBody.getUserId());
//
//        if (cart == null) {
//            return this.responseBuilder.buildResponse(HttpStatus.NOT_FOUND, "",
//                    "User doesn't have a cart", null);
//        }
//
//        Order newOrder = this.orderService.createOrder(user, cart, confirmOnlinePaymentBody.getShippingAddress());
//
//        return this.responseBuilder.buildResponse(HttpStatus.CREATED, "Order created with success.", "", newOrder);
//    }
//
//
//    @GetMapping(path = "payments")
//    public List<Payment> getPayments() {
//        return this.paymentService.getAll();
//    }
//
//    @GetMapping(path = "payments/{paymentId}")
//    public Payment get(@PathVariable("paymentId") Long paymentId) {
//        return this.paymentService.getById(paymentId);
//    }
//
//    @PostMapping(path = "payments")
//    public Payment add(@RequestBody Payment payment) {
//        return this.paymentService.addOrUpdate(payment);
//    }
//
//    @DeleteMapping(path = "payments/{paymentId}")
//    public void remove(@PathVariable("paymentId") Long paymentId) {
//        this.paymentService.remove(paymentId);
//    }
//
//    @PutMapping(path = "/payments{paymentId}")
//    public void update(@PathVariable("paymentId") Long paymentId,
//                       @RequestBody Payment payment) {
//        payment.setId(paymentId);
//        this.paymentService.addOrUpdate(payment);
//    }
}
