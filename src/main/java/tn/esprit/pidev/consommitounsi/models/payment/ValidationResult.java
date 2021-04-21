package tn.esprit.pidev.consommitounsi.models.payment;

public class ValidationResult {
    private boolean valid;
    private String validationError;

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public String getValidationError() {
        return validationError;
    }

    public void setValidationError(String validationError) {
        this.validationError = validationError;
    }
}
