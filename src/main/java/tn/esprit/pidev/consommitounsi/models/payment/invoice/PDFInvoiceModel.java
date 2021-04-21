package tn.esprit.pidev.consommitounsi.models.payment.invoice;

import java.util.Calendar;
import java.util.List;

// Reference: https://github.com/Invoiced/invoice-generator-api#api-reference
public class PDFInvoiceModel {

    private String logo = "https://i.imgur.com/Mc6d4Zc.jpg";
    private String from = "Consommi Tounsi";
    private String to = "Generic User";
    private String ship_to = "123, Main St.";
    private String number;
    private String currency = "TND";
    private Calendar date;
    private Calendar due_date;
    private List<InlineInvoiceItem> items;
    private Fields fields;
    private double discounts;
    private double tax;
    private double shipping;
    private double amount_paid;
    private String notes;
    private String terms;

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getShip_to() {
        return ship_to;
    }

    public void setShip_to(String ship_to) {
        this.ship_to = ship_to;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public Calendar getDue_date() {
        return due_date;
    }

    public void setDue_date(Calendar due_date) {
        this.due_date = due_date;
    }

    public List<InlineInvoiceItem> getItems() {
        return items;
    }

    public void setItems(List<InlineInvoiceItem> items) {
        this.items = items;
    }

    public Fields getFields() {
        return fields;
    }

    public void setFields(Fields fields) {
        this.fields = fields;
    }

    public double getDiscounts() {
        return discounts;
    }

    public void setDiscounts(double discounts) {
        this.discounts = discounts;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public double getShipping() {
        return shipping;
    }

    public void setShipping(double shipping) {
        this.shipping = shipping;
    }

    public double getAmount_paid() {
        return amount_paid;
    }

    public void setAmount_paid(double amount_paid) {
        this.amount_paid = amount_paid;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getTerms() {
        return terms;
    }

    public void setTerms(String terms) {
        this.terms = terms;
    }
}
