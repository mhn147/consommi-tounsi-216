package tn.esprit.pidev.consommitounsi.models.payment;

public class CartItem {
    private int quantity;
    private long productId;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }
}
