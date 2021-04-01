package tn.esprit.pidev.consommitounsi.models.payment.invoice;

public class Fields {
    private String tax = "%";
    private boolean discounts = false;
    private boolean shipping = true;

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public boolean isDiscounts() {
        return discounts;
    }

    public void setDiscounts(boolean discounts) {
        this.discounts = discounts;
    }

    public boolean isShipping() {
        return shipping;
    }

    public void setShipping(boolean shipping) {
        this.shipping = shipping;
    }
}
