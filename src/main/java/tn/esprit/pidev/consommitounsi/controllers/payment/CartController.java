package tn.esprit.pidev.consommitounsi.controllers.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import tn.esprit.pidev.consommitounsi.entities.payment.Item;
import tn.esprit.pidev.consommitounsi.entities.payment.Order;
import tn.esprit.pidev.consommitounsi.models.payment.ResponseModel;
import tn.esprit.pidev.consommitounsi.services.payment.ICartService;
import tn.esprit.pidev.consommitounsi.services.payment.IItemService;
import tn.esprit.pidev.consommitounsi.services.payment.IOrderService;
import tn.esprit.pidev.consommitounsi.services.payment.ItemService;

@RestController
@RequestMapping(path = "cart")
public class CartController {

    private final IOrderService orderService;
    private final IItemService itemService;
    private final ICartService cartService;

    @Autowired
    public CartController(IOrderService orderService, IItemService itemService, ICartService cartService) {
        this.orderService = orderService;
        this.itemService = itemService;
        this.cartService = cartService;
    }

    @PostMapping("{cartId}/items")
    public ResponseEntity<ResponseModel<Order>> addItem(@PathVariable("cartId") Long cartId,
                                                       @RequestBody Item item) {
        if (item == null) {
            return this.buildResponse(HttpStatus.BAD_REQUEST, "",
                    "The cart-item is empty.", null);
        }

        if (item.getQuantity() <= 0 || item.getProduct() == null) {
            return this.buildResponse(HttpStatus.BAD_REQUEST, "",
                    "The cart-item's product is empty or invalid.", null);
        }


        if (this.cartService.itemProductExistsInCart(cartId, item.getProduct().getId())) {
            return this.buildResponse(HttpStatus.BAD_REQUEST, "",
                    "The cart already contains this product.", null);
        }

        this.itemService.addOrUpdate(item);
        this.cartService.addItem(cartId, item);

        return this.buildResponse(HttpStatus.CREATED, "The cart already contains this product.",
                "", null);
    }

    @PatchMapping("{cartId}/items/{itemId}")
    public ResponseEntity<ResponseModel<Order>> updateQantity(@PathVariable("cartId") Long cartId,
                                                              @PathVariable("itemId") Long itemId,
                                                              @RequestBody int quantity) {
        Item item = this.itemService.getById(itemId);
        if (item == null) {
            return this.buildResponse(HttpStatus.BAD_REQUEST, "",
                    "The cart-item does not exist.", null);
        }
        if (quantity <= 0 || item.getProduct() == null) {
            return this.buildResponse(HttpStatus.BAD_REQUEST, "",
                    "The cart-item's product is empty or invalid.", null);
        }

        this.itemService.updateItemQuantity(item, quantity);
        Order cart = this.orderService.getById(cartId);
        return this.buildResponse(HttpStatus.OK, "Cart-item's quantity has changed to " + quantity,
                "",
                cart);
    }

    private ResponseEntity<ResponseModel<Order>> buildResponse(HttpStatus httpStatus, String success, String error, Order body) {
        ResponseModel<Order> response = new ResponseModel<>(success,
                error,
                body);
        return ResponseEntity.status(httpStatus).body(response);
    }
}