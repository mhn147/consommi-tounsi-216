package tn.esprit.pidev.consommitounsi.controllers.payment.helpers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import tn.esprit.pidev.consommitounsi.entities.payment.Item;
import tn.esprit.pidev.consommitounsi.models.payment.ResponseModel;

@Component
public class ResponseBuilder<T> implements IResponseBuilder<T> {

    public ResponseEntity<ResponseModel<T>> buildResponse(
            HttpStatus httpStatus, String success, String error, T body) {
        ResponseModel<T> response = new ResponseModel<>(success,
                error,
                body);
        return ResponseEntity.status(httpStatus).body(response);
    }

    public ResponseEntity<ResponseModel<T>> notFoundResponse(String errorMessage) {
        return this.buildResponse(HttpStatus.NOT_FOUND, null,
                errorMessage, null);
    }

    public ResponseEntity<ResponseModel<T>> createdResponse(T body, String successMessage) {
        return this.buildResponse(HttpStatus.CREATED, successMessage,
                null, body);
    }

    public ResponseEntity<ResponseModel<T>> okResponse(T body, String successMessage) {
        return this.buildResponse(HttpStatus.OK, successMessage,
                null, body);
    }

    public ResponseEntity<ResponseModel<T>> badRequestResponse(String errorMessage) {
        return this.buildResponse(HttpStatus.BAD_REQUEST, null,
                errorMessage, null);
    }
}
