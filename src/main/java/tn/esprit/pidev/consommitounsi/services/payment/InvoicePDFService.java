package tn.esprit.pidev.consommitounsi.services.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tn.esprit.pidev.consommitounsi.config.PDFInvoiceStorageProperty;
import tn.esprit.pidev.consommitounsi.entities.payment.Invoice;
import tn.esprit.pidev.consommitounsi.entities.payment.Item;
import tn.esprit.pidev.consommitounsi.models.payment.invoice.Fields;
import tn.esprit.pidev.consommitounsi.models.payment.invoice.InlineInvoiceItem;
import tn.esprit.pidev.consommitounsi.models.payment.invoice.PDFInvoiceModel;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class InvoicePDFService implements IInvoicePDFService {
    private Path fileDirectoryPath;

    private final RestTemplate restTemplate;
    @Autowired
    private final PDFInvoiceStorageProperty pdfInvoiceStorageProperty;


    public InvoicePDFService(RestTemplateBuilder restTemplateBuilder, PDFInvoiceStorageProperty pdfInvoiceStorageProperty)
            throws IOException {
        this.pdfInvoiceStorageProperty = pdfInvoiceStorageProperty;
        this.fileDirectoryPath = Paths.get(pdfInvoiceStorageProperty.getUploadDirectory()).toAbsolutePath().normalize();
        Files.createDirectories(this.fileDirectoryPath);
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public byte[] getFile(Invoice invoice) throws IOException {
        File file = new File(invoice.getInvoiceFilePath());

        return Files.readAllBytes(file.toPath());
    }

    public String saveInvoicePDF(Invoice invoice, String userFullName, String address, boolean isPayed) throws InterruptedException  {
        String filename = Long.toString(Calendar.getInstance().getTimeInMillis()) + ".pdf";
        Path targetLocation = this.fileDirectoryPath.resolve(filename);

        CompletableFuture<byte[]> file = this.getInvoicePDF(invoice, userFullName, address, isPayed).whenComplete((byteArray, throwable) -> {
            InputStream inputStream = new ByteArrayInputStream(byteArray);
            try {
                Files.copy(inputStream, targetLocation);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        return targetLocation.toString();
    }

    @Async
    protected CompletableFuture<byte[]> getInvoicePDF(Invoice invoice, String userFullName,
                                                      String address, boolean isPayed) throws InterruptedException {
        PDFInvoiceModel invoiceModel = this.mapToInvoiceModel(invoice, userFullName, address, isPayed);
        String url = "https://invoice-generator.com";
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT_LANGUAGE, "fr-FR");
        headers.set(HttpHeaders.CONTENT_TYPE, "application/json");
        headers.set(HttpHeaders.ACCEPT, "*/*");

        final HttpEntity<PDFInvoiceModel> entity = new HttpEntity<PDFInvoiceModel>(invoiceModel, headers);

        ResponseEntity<byte[]> response = restTemplate.exchange(url, HttpMethod.POST, entity, byte[].class);
        byte[] pdfFile = response.getBody();


        return CompletableFuture.completedFuture(pdfFile);
    }

    private PDFInvoiceModel mapToInvoiceModel(Invoice invoice, String userFullName, String address, boolean isPayed) {
        PDFInvoiceModel pdfModel = new PDFInvoiceModel();

        pdfModel.setNumber(Long.toString(invoice.getInvoiceNumber()));
        pdfModel.setTo(userFullName);
        pdfModel.setShip_to(address);
        pdfModel.setDate(invoice.getInvoiceDate());
        Calendar dueDate = invoice.getInvoiceDate();
        dueDate.add(Calendar.DATE, 60);
        pdfModel.setDue_date(dueDate);
        pdfModel.setItems(this.mapInvoiceItemsToPDFInvoiceItems(invoice.getOrder().getItems()));
        pdfModel.setFields(new Fields());
        pdfModel.setTax(10);
        pdfModel.setShipping(1);
        if (isPayed) {
            pdfModel.setAmount_paid(invoice.getTotal());
        }


        return pdfModel;
    }

    private List<InlineInvoiceItem> mapInvoiceItemsToPDFInvoiceItems(List<Item> items) {
        List<InlineInvoiceItem> invoiceItems = new ArrayList<>();
        for (Item item:
             items) {
            InlineInvoiceItem invoiceItem = new InlineInvoiceItem();
            invoiceItem.setDescription("");
            invoiceItem.setName(item.getProduct().getName());
            invoiceItem.setQuantity(item.getQuantity());
            invoiceItem.setUnit_cost(item.getProduct().getPrice());
            invoiceItems.add(invoiceItem);
        }

        return invoiceItems;
    }
}
