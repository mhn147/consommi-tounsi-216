//package tn.esprit.pidev.consommitounsi.controllers.payment;
//
//import org.apache.commons.io.IOUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//import tn.esprit.pidev.consommitounsi.entities.payment.Invoice;
//import tn.esprit.pidev.consommitounsi.services.payment.IInvoicePDFService;
//import tn.esprit.pidev.consommitounsi.services.payment.IInvoiceService;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.List;
//
//@RestController
//@RequestMapping
//public class InvoiceController {
//
//    private final IInvoiceService invoiceService;
//    private final IInvoicePDFService invoicePDFService;
//
//    @Autowired
//    public InvoiceController(IInvoiceService invoiceService, IInvoicePDFService invoicePDFService) {
//        this.invoicePDFService = invoicePDFService;
//        this.invoiceService = invoiceService;
//    }
//
//    @GetMapping(path = "invoices")
//    public List<Invoice> getInvoices() {
//        return this.invoiceService.getAll();
//    }
//
//    @GetMapping(path = "invoices/{invoiceId}")
//    public Invoice get(@PathVariable("invoiceId") Long invoiceId) {
//        return this.invoiceService.getById(invoiceId);
//    }
//
//    @PostMapping(path = "invoices")
//    public Invoice add(@RequestBody Invoice invoice) {
//        return this.invoiceService.addOrUpdate(invoice);
//    }
//
//    @DeleteMapping(path = "invoices/{invoiceId}")
//    public void remove(@PathVariable("invoiceId") Long invoiceId) {
//        this.invoiceService.remove(invoiceId);
//    }
//
//    @PutMapping(path = "invoices/{invoiceNumber}")
//    public void update(@PathVariable("invoiceNumber") Long invoiceNumber,
//                       @RequestBody Invoice invoice) {
//        invoice.setInvoiceNumber(invoiceNumber);
//        this.invoiceService.addOrUpdate(invoice);
//    }
//
//    @GetMapping(path = "invoices/pdf/{invoiceNumber}")
//    public @ResponseBody byte[] getInvoicePDF(@PathVariable("invoiceNumber") Long invoiceNumber) throws IOException {
//        Invoice invoice = this.invoiceService.getById(invoiceNumber);
//
//        if (invoice == null) {
//            return null;
//        }
//
//        return this.invoicePDFService.getFile(invoice);
//    }
//}
