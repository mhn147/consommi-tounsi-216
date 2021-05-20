package tn.esprit.pidev.consommitounsi.controllers.payment;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidev.consommitounsi.entities.payment.Payment;
import tn.esprit.pidev.consommitounsi.services.payment.interfaces.IPaymentService;
import tn.esprit.pidev.consommitounsi.services.user.IUserService;

import java.util.List;

@RestController
@RequestMapping
public class PaymentController {

    private final IUserService userService;
    private final IPaymentService paymentService;

    @Autowired
    public PaymentController (IPaymentService paymentService,
                               IUserService userService) {
        this.paymentService = paymentService;
        this.userService = userService;
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
