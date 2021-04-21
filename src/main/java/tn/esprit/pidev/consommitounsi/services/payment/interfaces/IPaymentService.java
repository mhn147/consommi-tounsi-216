package tn.esprit.pidev.consommitounsi.services.payment.interfaces;

import tn.esprit.pidev.consommitounsi.entities.payment.Payment;

import java.util.List;

public interface IPaymentService extends IService<Payment> {
    List<Payment> getAll();
    Payment getById(Long id);
    Payment addOrUpdate(Payment entity);
    void remove(Long id);
}
