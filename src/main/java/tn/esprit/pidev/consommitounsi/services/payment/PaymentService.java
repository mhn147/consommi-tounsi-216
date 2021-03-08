package tn.esprit.pidev.consommitounsi.services.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pidev.consommitounsi.entities.payment.Payment;
import tn.esprit.pidev.consommitounsi.repositories.payment.IPaymentRepository;
import tn.esprit.pidev.consommitounsi.services.common.IService;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PaymentService implements IPaymentService, IService<Payment> {
    @Autowired
    private final IPaymentRepository paymentRepository;

    @Autowired
    public PaymentService(IPaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public List<Payment> getAll() {
        return (List<Payment>) paymentRepository.findAll();
    }

    @Override
    public Payment getById(Long id) {
        return this.paymentRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("Payment with id " + id + " does not exist.")
        );
    }

    @Override
    public Payment addOrUpdate(Payment payment) {
        return this.paymentRepository.save(payment);
    }

    @Override
    public void remove(Long id) {
        Payment payment = this.getById(id);
        paymentRepository.delete(payment);
    }
}
