package tn.esprit.pidev.consommitounsi.repositories.payment;

import org.springframework.data.repository.CrudRepository;
import tn.esprit.pidev.consommitounsi.entities.payment.Payment;

public interface IPaymentRepository extends CrudRepository<Payment, Long> {
}