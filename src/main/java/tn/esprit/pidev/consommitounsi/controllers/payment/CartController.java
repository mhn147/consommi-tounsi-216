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
    public ResponseEntity<ResponseModel<Item>> addItem(@PathVariable("cartId") Long cartId,
                                                       @RequestBody Item item) {
        if (item == null) {
            ResponseModel<Item> response = new ResponseModel<>("",
                    "The cart item cannot be empty.",
                    null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        if (item.getQuantity() <= 0 || item.getProduct() == null) {
            ResponseModel<Item> response = new ResponseModel<>("",
                    "The cart-item's product is empty or invalid.",
                    null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }


        if (this.cartService.itemProductExistsInCart(cartId, item.getProduct().getId())) {
            ResponseModel<Item> response = new ResponseModel<>("",
                "The cart already contains this product.",
                null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        this.itemService.addOrUpdate(item);
        this.cartService.addItem(cartId, item);

        ResponseModel<Item> response = new ResponseModel<>("Item added to cart #" + cartId + ".",
                "",
                item);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}