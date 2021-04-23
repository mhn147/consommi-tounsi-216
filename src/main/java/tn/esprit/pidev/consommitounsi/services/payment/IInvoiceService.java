package tn.esprit.pidev.consommitounsi.services.payment;

import tn.esprit.pidev.consommitounsi.entities.common.Address;
import tn.esprit.pidev.consommitounsi.entities.payment.Invoice;
import tn.esprit.pidev.consommitounsi.entities.payment.Item;
import tn.esprit.pidev.consommitounsi.entities.payment.Order;
import tn.esprit.pidev.consommitounsi.entities.user.User;

import java.util.List;

public interface IInvoiceService {
    Invoice createInvoice(User user, Order order, List<Item> items, Address shippingAddress, boolean isPayed) throws InterruptedException;
}
