package tn.esprit.pidev.consommitounsi.services.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pidev.consommitounsi.entities.payment.Invoice;
import tn.esprit.pidev.consommitounsi.repositories.payment.IInvoiceRepository;
import tn.esprit.pidev.consommitounsi.services.common.IService;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class InvoiceService implements IInvoiceService, IService<Invoice> {

    private final IInvoiceRepository invoiceRepository;

    @Autowired
    public InvoiceService(IInvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    @Override
    public List<Invoice> getAll() {
        return (List<Invoice>)invoiceRepository.findAll();
    }

    @Override
    public Invoice getById(Long id) {
        return this.invoiceRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("Invoice with id " + id + " does not exist.")
        );
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
}
