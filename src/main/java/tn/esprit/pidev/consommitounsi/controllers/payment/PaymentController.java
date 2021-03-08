package tn.esprit.pidev.consommitounsi.controllers.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidev.consommitounsi.entities.payment.Payment;
import tn.esprit.pidev.consommitounsi.services.payment.IPaymentService;

import java.util.List;

@RestController
@RequestMapping(path = "payments")
public class PaymentController {

    private final IPaymentService paymentService;

    @Autowired
    public PaymentController(IPaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping
    public List<Payment> getPayments() {
        return this.paymentService.getAll();
    }

    @GetMapping(path = "{paymentId}")
    public Payment get(@PathVariable("paymentId") Long paymentId) {
        return this.paymentService.getById(paymentId);
    }

    @PostMapping
    public Payment add(@RequestBody Payment payment) {
        return this.paymentService.addOrUpdate(payment);
    }

    @DeleteMapping(path = "{paymentId}")
    public void remove(@PathVariable("paymentId") Long paymentId) {
        this.paymentService.remove(paymentId);
    }

    @PutMapping(path = "{paymentId}")
    public void update(@PathVariable("paymentId") Long paymentId,
                       @RequestBody Payment payment) {
        payment.setId(paymentId);
        this.paymentService.addOrUpdate(payment);
    }
}
