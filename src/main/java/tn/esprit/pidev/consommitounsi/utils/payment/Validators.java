package tn.esprit.pidev.consommitounsi.utils.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import tn.esprit.pidev.consommitounsi.entities.payment.Order;
import tn.esprit.pidev.consommitounsi.models.payment.CartItem;
import tn.esprit.pidev.consommitounsi.models.payment.ResponseModel;
import tn.esprit.pidev.consommitounsi.services.products.IProductService;
import tn.esprit.pidev.consommitounsi.services.products.ProductService;

@Component
public class Validators {
     private final ProductService productService;
     private final ResponseBuilder responseBuilder;

     @Autowired
     public Validators(ProductService productService,
                       ResponseBuilder responseBuilder) {
         this.productService = productService;
         this.responseBuilder = responseBuilder;
     }

     public <Order> ResponseEntity<ResponseModel<Order>> validateCartItem(CartItem cartItem) {
        if (cartItem == null) {
            return this.responseBuilder.buildResponse(HttpStatus.BAD_REQUEST, "",
                    "Cart's item is empty.", null);
        }

        if (cartItem.getQuantity() <= 0) {
            return this.responseBuilder.buildResponse(HttpStatus.BAD_REQUEST, "",
                    "Invalid item quantity value.", null);
        }

        if (cartItem.getProductId() <= 0) {
            return this.responseBuilder.buildResponse(HttpStatus.BAD_REQUEST, "",
                    "Invalid Cart-item's product.", null);
        }


        if (this.productService.getProductById(cartItem.getProductId()) == null) {
            return this.responseBuilder.buildResponse(HttpStatus.BAD_REQUEST, "",
                    "Cart-item's product doesn't exist.", null);
        }
        return null;
    }
}
