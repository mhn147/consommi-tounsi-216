package tn.esprit.pidev.consommitounsi.services.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pidev.consommitounsi.entities.payment.OnlinePayment;
import tn.esprit.pidev.consommitounsi.entities.payment.Payment;
import tn.esprit.pidev.consommitounsi.repositories.payment.IPaymentRepository;
import tn.esprit.pidev.consommitounsi.services.common.IService;

import java.util.List;

@Service
public class OnlinePaymentService extends PaymentService implements IOnlinePaymentService {

    @Autowired
    public OnlinePaymentService(IPaymentRepository paymentRepository) {
        super(paymentRepository);
    }
}
