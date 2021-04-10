package tn.esprit.pidev.consommitounsi.services.payment.interfaces;

import tn.esprit.pidev.consommitounsi.entities.payment.Invoice;

import java.util.List;

public interface IInvoiceService {
    List<Invoice> getAll();
    Invoice getById(Long id);
    Invoice addOrUpdate(Invoice entity);
    void remove(Long id);
}
