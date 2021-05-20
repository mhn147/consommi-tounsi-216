package tn.esprit.pidev.consommitounsi.services.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pidev.consommitounsi.entities.common.Address;
import tn.esprit.pidev.consommitounsi.entities.payment.Invoice;
import tn.esprit.pidev.consommitounsi.entities.payment.Item;
import tn.esprit.pidev.consommitounsi.entities.payment.Order;
import tn.esprit.pidev.consommitounsi.entities.user.User;
import tn.esprit.pidev.consommitounsi.repositories.payment.IInvoiceRepository;
import tn.esprit.pidev.consommitounsi.services.payment.interfaces.IInvoiceService;

import java.util.List;

@Service
public class InvoiceService implements IInvoiceService {

    private final IInvoiceRepository invoiceRepository;
    private final IInvoicePDFService invoicePDFService;

    @Autowired
    public InvoiceService(IInvoiceRepository invoiceRepository,
                          IInvoicePDFService invoicePDFService) {
        this.invoicePDFService = invoicePDFService;
        this.invoiceRepository = invoiceRepository;
    }

    @Override
    public List<Invoice> getAll() {
        return (List<Invoice>)invoiceRepository.findAll();
    }

    @Override
    public Invoice getById(Long id) {
        return this.invoiceRepository.findById(id).orElse(null);
    }

    @Override
    public Invoice addOrUpdate(Invoice invoice) {
        return this.invoiceRepository.save(invoice);
    }

    @Override
    public void remove(Long id) {
        Invoice invoice = this.getById(id);
        invoiceRepository.delete(invoice);
    }


    public Invoice createInvoice(User user, Order order, Address shippingAddress,
                                 boolean isPayed) throws InterruptedException {
        Invoice newInvoice = new Invoice(user, order);
        String invoiceFilePath = this.invoicePDFService.saveInvoicePDF(newInvoice,
                user.getFirstName() + " " + user.getLastName(),
                shippingAddress.toString(),
                isPayed);
        newInvoice.setInvoiceFilePath(invoiceFilePath);
        return this.invoiceRepository.save(newInvoice);
    }
}
