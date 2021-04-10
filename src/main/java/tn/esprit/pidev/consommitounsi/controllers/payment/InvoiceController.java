package tn.esprit.pidev.consommitounsi.controllers.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidev.consommitounsi.entities.payment.Invoice;
import tn.esprit.pidev.consommitounsi.services.payment.interfaces.IInvoiceService;

import java.util.List;

@RestController
@RequestMapping
public class InvoiceController {

    private final IInvoiceService invoiceService;

    @Autowired
    public InvoiceController(IInvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping(path = "invoices")
    public List<Invoice> getInvoices() {
        return this.invoiceService.getAll();
    }

    @GetMapping(path = "invoices/{invoiceId}")
    public Invoice get(@PathVariable("invoiceId") Long invoiceId) {
        return this.invoiceService.getById(invoiceId);
    }

    @PostMapping(path = "invoices")
    public Invoice add(@RequestBody Invoice invoice) {
        return this.invoiceService.addOrUpdate(invoice);
    }

    @DeleteMapping(path = "invoices/{invoiceId}")
    public void remove(@PathVariable("invoiceId") Long invoiceId) {
        this.invoiceService.remove(invoiceId);
    }

    @PutMapping(path = "invoices/{invoiceNumber}")
    public void update(@PathVariable("invoiceNumber") Long invoiceNumber,
                       @RequestBody Invoice invoice) {
        invoice.setInvoiceNumber(invoiceNumber);
        this.invoiceService.addOrUpdate(invoice);
    }
}
