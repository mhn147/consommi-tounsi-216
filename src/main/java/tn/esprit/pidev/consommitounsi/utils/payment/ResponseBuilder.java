package tn.esprit.pidev.consommitounsi.utils.payment;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import tn.esprit.pidev.consommitounsi.entities.payment.Order;
import tn.esprit.pidev.consommitounsi.models.payment.ResponseModel;

@Component
public class ResponseBuilder {
    public <T> ResponseEntity<ResponseModel<T>> buildResponse(HttpStatus httpStatus, String success, String error, T body) {
        ResponseModel<T> response = new ResponseModel<>(success,
                error,
                body);
        return ResponseEntity.status(httpStatus).body(response);
    }
}
