package tn.esprit.pidev.consommitounsi.controllers.payment.helpers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import tn.esprit.pidev.consommitounsi.entities.payment.Item;
import tn.esprit.pidev.consommitounsi.models.payment.ResponseModel;

@Component
public class ItemResponseBuilder implements IResponseBuilder<Item> {

    private ResponseEntity<ResponseModel<Item>> _buildResponse(HttpStatus httpStatus, String success, String error, Item body) {
        ResponseModel<Item> response = new ResponseModel<>(success,
                error,
                body);
        return ResponseEntity.status(httpStatus).body(response);
    }

    public ResponseEntity<ResponseModel<Item>> notFoundResponse() {
        return _buildResponse(HttpStatus.NOT_FOUND, null,
                "Item doesn't exist", null);
    }

    public ResponseEntity<ResponseModel<Item>> createdResponse(Item body) {
        return _buildResponse(HttpStatus.CREATED, "Item created or updated with success.",
                null, body);
    }

    public ResponseEntity<ResponseModel<Item>> okResponse(Item body, String successMessage) {
        return _buildResponse(HttpStatus.OK, successMessage,
                null, body);
    }

    public ResponseEntity<ResponseModel<Item>> badRequestResponse(String errorMessage) {
        return _buildResponse(HttpStatus.BAD_REQUEST, null,
                errorMessage, null);
    }
}
