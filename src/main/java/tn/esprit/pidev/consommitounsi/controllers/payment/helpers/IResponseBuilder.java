package tn.esprit.pidev.consommitounsi.controllers.payment.helpers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import tn.esprit.pidev.consommitounsi.models.payment.ResponseModel;

public interface IResponseBuilder<T> {
    ResponseEntity<ResponseModel<T>> notFoundResponse();
    ResponseEntity<ResponseModel<T>> createdResponse(T body);
    ResponseEntity<ResponseModel<T>> okResponse(T body, String successMessage);
    ResponseEntity<ResponseModel<T>> badRequestResponse(String errorMessage);
}
