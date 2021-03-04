package tn.esprit.pidev.consommitounsi.controllers.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidev.consommitounsi.entities.payment.Invoice;
import tn.esprit.pidev.consommitounsi.services.payment.IInvoiceService;

import java.util.List;

@RestController
@RequestMapping(path = "invoices")
public class InvoiceController {

    private final IInvoiceService invoiceService;

    @Autowired
    public InvoiceController(IInvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping
    public List<Invoice> getInvoices() {
        return this.invoiceService.getAll();
    }

    @GetMapping(path = "{invoiceId}")
    public Invoice get(@PathVariable("invoiceId") Long invoiceId) {
        return this.invoiceService.getById(invoiceId);
    }

    @PostMapping
    public Invoice add(@RequestBody Invoice invoice) {
        return this.invoiceService.add(invoice);
    }

    @DeleteMapping(path = "{invoiceId}")
    public void remove(@PathVariable("invoiceId") Long invoiceId) {
        this.invoiceService.remove(invoiceId);
    }

    @PutMapping(path = "{invoiceId}")
    public void update(@PathVariable("invoiceId") Long invoiceId,
                       @RequestBody Invoice invoice) {
        this.invoiceService.update(invoice, invoiceId);
    }
}
