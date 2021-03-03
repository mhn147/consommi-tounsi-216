package tn.esprit.pidev.consommitounsi.services.payment;

import org.springframework.stereotype.Service;
import tn.esprit.pidev.consommitounsi.repositories.payment.IPaymentRepository;

@Service
public class AtDeliveryPaymentService extends PaymentService implements IAtDeliveryPaymentService {

    public AtDeliveryPaymentService(IPaymentRepository paymentRepository) {
        super(paymentRepository);
    }
}
