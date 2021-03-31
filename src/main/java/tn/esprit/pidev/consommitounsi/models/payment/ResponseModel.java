package tn.esprit.pidev.consommitounsi.models.payment;

import java.io.Serializable;

public class ResponseModel<T> implements Serializable {
    private String success;
    private String error;
    private T body;

    public ResponseModel() {}

    public ResponseModel(String success, String error, T body) {
        this.success = success;
        this.error = error;
        this.body = body;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }
}
