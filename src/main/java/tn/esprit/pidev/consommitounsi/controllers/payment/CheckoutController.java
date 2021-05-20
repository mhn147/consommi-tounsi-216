package tn.esprit.pidev.consommitounsi.controllers.payment;

import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidev.consommitounsi.controllers.payment.helpers.IResponseBuilder;
import tn.esprit.pidev.consommitounsi.controllers.payment.helpers.ResponseBuilder;
import tn.esprit.pidev.consommitounsi.entities.payment.Item;
import tn.esprit.pidev.consommitounsi.entities.payment.Order;
import tn.esprit.pidev.consommitounsi.entities.payment.Payment;
import tn.esprit.pidev.consommitounsi.entities.payment.PaymentType;
import tn.esprit.pidev.consommitounsi.entities.user.User;
import tn.esprit.pidev.consommitounsi.models.payment.*;
import tn.esprit.pidev.consommitounsi.services.payment.interfaces.ICheckoutService;
import tn.esprit.pidev.consommitounsi.services.payment.interfaces.IPaymentService;
import tn.esprit.pidev.consommitounsi.services.payment.interfaces.ITaxesService;
import tn.esprit.pidev.consommitounsi.services.user.IUserService;

import java.util.List;

@RestController
@RequestMapping
public class CheckoutController {

    private final IUserService userService;
    private final IPaymentService paymentService;
    private final ICheckoutService checkoutService;
    private final IResponseBuilder<Order> responseBuilder;
    private final ITaxesService taxesService;

    @Autowired
    public CheckoutController (IPaymentService paymentService,
                               IUserService userService,
                               ICheckoutService checkoutService,
                               ResponseBuilder<Order> responseBuilder,
                               ITaxesService taxesService) {
        this.paymentService = paymentService;
        this.userService = userService;
        this.checkoutService = checkoutService;
        this.responseBuilder = responseBuilder;
        this.taxesService = taxesService;
    }

    @PostMapping(path = "atDeliveryCheckout")
    public ResponseEntity createAtDeliveryPayment(@RequestBody PaymentModel payment) {
        Order order = this.checkoutService.createAtDeliveryOrder(payment);
        return this.responseBuilder.createdResponse(order, "Order created");
    }


    @PostMapping(path = "payments/online/create-intent")
    public CreatePaymentResponse createOnlinePaymentIntent(
            @RequestBody CreatePaymentBody createPaymentBody)

    {
        long amountInCents = (long)(this.taxesService.calculateTaxes(createPaymentBody.getItems())
                    + this.taxesService.calculateSubTotal(createPaymentBody.getItems()) * 100);
        PaymentIntentCreateParams createParams = new PaymentIntentCreateParams.Builder()
                    .setCurrency(createPaymentBody.getCurrency())
                    .setAmount(amountInCents)
                    .build();

        // Create a PaymentIntent with the order amount and currency
        try {
            PaymentIntent intent = PaymentIntent.create(createParams);
            // Send publishable key and PaymentIntent details to client
            // TODO MOVE PUBLIC KEY TO ENV VAR
            return new CreatePaymentResponse(
                    "pk_test_51IlUUVCoa56OaMeIymsYtWSqoEYS59GfLqQFvudoeg2Wj8eFXtGxPTnvQ20zDVdh9ctWPXWJgF2CC2zpO4JlwPYv0096u5j2sV", intent.getClientSecret());
        } catch (StripeException ex) {
            return null;
        }
    }



    @PostMapping(path = "payments/online/confirmCardPayment")
    public ResponseEntity<ResponseModel<Order>> confirmOnlinePayment(
            @RequestBody ConfirmOnlinePaymentBody confirmOnlinePaymentBody) throws InterruptedException {
        PaymentModel paymentModel = new PaymentModel();
        paymentModel.setPaymentType(PaymentType.ONLINE);
        paymentModel.setShippingAddress(confirmOnlinePaymentBody.getShippingAddress());
        paymentModel.setUserId(confirmOnlinePaymentBody.getUserId());
        Order order = this.checkoutService.createOnlineOrder(paymentModel);
        return this.responseBuilder.createdResponse(order, "Order created");
    }
}
