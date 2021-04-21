package tn.esprit.pidev.consommitounsi.services.payment;

import tn.esprit.pidev.consommitounsi.entities.payment.Invoice;

import java.io.IOException;

public interface IInvoicePDFService {
    String saveInvoicePDF(Invoice invoice, String userFullName, String address, boolean isPayed) throws InterruptedException;
    byte[] getFile(Invoice invoice) throws IOException;
}
