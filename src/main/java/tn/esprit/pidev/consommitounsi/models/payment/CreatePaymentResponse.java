package tn.esprit.pidev.consommitounsi.models.payment;

public class CreatePaymentResponse {
    private String publishableKey;
    private String clientSecret;

    public CreatePaymentResponse(String publishableKey, String clientSecret) {
        this.publishableKey = publishableKey;
        this.clientSecret = clientSecret;
    }

    public String getPublishableKey() {
        return publishableKey;
    }

    public void setPublishableKey(String publishableKey) {
        this.publishableKey = publishableKey;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }
}
