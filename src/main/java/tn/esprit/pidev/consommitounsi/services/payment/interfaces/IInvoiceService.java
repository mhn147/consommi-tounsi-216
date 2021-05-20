package tn.esprit.pidev.consommitounsi.services.payment.interfaces;

import tn.esprit.pidev.consommitounsi.entities.common.Address;
import tn.esprit.pidev.consommitounsi.entities.payment.Invoice;
import tn.esprit.pidev.consommitounsi.entities.payment.Order;
import tn.esprit.pidev.consommitounsi.entities.user.User;

import java.util.List;

public interface IInvoiceService {
    List<Invoice> getAll();
    Invoice getById(Long id);
    Invoice addOrUpdate(Invoice entity);
    void remove(Long id);
    Invoice createInvoice(User user, Order order, Address shippingAddress,
                                 boolean isPayed) throws InterruptedException ;

}
