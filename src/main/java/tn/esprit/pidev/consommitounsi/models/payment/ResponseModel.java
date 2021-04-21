package tn.esprit.pidev.consommitounsi.models.payment;

import java.io.Serializable;

public class ResponseModel<T> implements Serializable {
    private String successMessage;
    private String errorMessage;
    private T body;

    public ResponseModel() {}

    public ResponseModel(String success, String error, T body) {
        this.successMessage = success;
        this.errorMessage = error;
        this.body = body;
    }

    public String getSuccessMessage() {
        return successMessage;
    }

    public void setSuccessMessage(String successMessage) {
        this.successMessage = successMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }
}
