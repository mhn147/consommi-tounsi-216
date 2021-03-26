package tn.esprit.pidev.consommitounsi.controllers.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidev.consommitounsi.entities.payment.Item;
import tn.esprit.pidev.consommitounsi.entities.payment.Order;
import tn.esprit.pidev.consommitounsi.entities.products.Product;
import tn.esprit.pidev.consommitounsi.models.payment.CartItem;
import tn.esprit.pidev.consommitounsi.models.payment.ResponseModel;
import tn.esprit.pidev.consommitounsi.services.payment.ICartService;
import tn.esprit.pidev.consommitounsi.services.payment.IItemService;
import tn.esprit.pidev.consommitounsi.services.payment.IOrderService;
import tn.esprit.pidev.consommitounsi.services.products.IProductService;
import tn.esprit.pidev.consommitounsi.services.products.ProductService;

@RestController
@RequestMapping(path = "cart")
public class CartController {

    private final IOrderService orderService;
    private final IItemService itemService;
    private final ICartService cartService;
    private final ProductService productService;

    @Autowired
    public CartController(IOrderService orderService,
                          IItemService itemService,
                          ICartService cartService,
                          ProductService productService) {
        this.orderService = orderService;
        this.itemService = itemService;
        this.cartService = cartService;
        this.productService = productService;
    }

    @GetMapping("{userId}")
    public ResponseEntity<ResponseModel<Order>> getCartByUserId(@PathVariable("userId") Long userId) {
        Order cart = this.cartService.getByUserId(userId);

        if (cart == null) {
            return this.buildResponse(HttpStatus.NOT_FOUND, "",
                    "User doesn't have a cart", cart);
        } else {
            return this.buildResponse(HttpStatus.OK, "",
                    "", cart);
        }
    }

    @PostMapping("{cartId}/items")
    public ResponseEntity<ResponseModel<Order>> addItem(@PathVariable("cartId") Long cartId,
                                                        @RequestBody CartItem cartItem) {

        ResponseEntity<ResponseModel<Order>> validationResult = validateCartItem(cartItem);
        if (validationResult != null) return validationResult;

        if (this.cartService.itemProductExistsInCart(cartId, cartItem.getProductId())) {
            return this.buildResponse(HttpStatus.BAD_REQUEST, "",
                    "Invalid Cart id.", null);
        }

        Product cartItemProduct = this.productService.getProductById(cartItem.getProductId());
        Item item = new Item(cartItem.getQuantity(), cartItemProduct);
        this.itemService.addOrUpdate(item);
        this.cartService.addItem(cartId, item);

        Order cart = this.orderService.getById(cartId);
        return this.buildResponse(HttpStatus.CREATED, "Cart-item added with success.",
                "", cart);
    }

    @PatchMapping("{cartId}/items/{itemId}")
    public ResponseEntity<ResponseModel<Order>> updateQuantity(@PathVariable("cartId") Long cartId,
                                                               @PathVariable("itemId") Long itemId,
                                                               @RequestBody CartItem cartItem) {

        ResponseEntity<ResponseModel<Order>> validationResult = validateCartItem(cartItem);
        if (validationResult != null) return validationResult;

        Item item = this.itemService.getById(itemId);
        if (item == null || !this.cartService.containsItem(cartId, itemId)) {
            return this.buildResponse(HttpStatus.BAD_REQUEST, "",
                    "The cart-item does not exist.", null);
        }

        this.itemService.updateItemQuantity(item, cartItem.getQuantity());
        Order cart = this.orderService.getById(cartId);
        return this.buildResponse(HttpStatus.OK, "Cart-item's updated with success.",
                "",
                cart);
    }

    @DeleteMapping("{cartId}/items/{itemId}")
    public ResponseEntity<ResponseModel<Order>> removeItem(@PathVariable("cartId") Long cartId,
                                                           @PathVariable("itemId") Long itemId) {
        Item item = this.itemService.getById(itemId);
        if (item == null) {
            return this.buildResponse(HttpStatus.BAD_REQUEST, "",
                    "The cart-item does not exist.", null);
        }

        Order cart = this.cartService.removeItem(cartId, item);
        return this.buildResponse(HttpStatus.OK, "Item has been removed from the cart.",
                "",
                cart);
    }

    private ResponseEntity<ResponseModel<Order>> buildResponse(HttpStatus httpStatus, String success, String error, Order body) {
        ResponseModel<Order> response = new ResponseModel<>(success,
                error,
                body);
        return ResponseEntity.status(httpStatus).body(response);
    }


    private ResponseEntity<ResponseModel<Order>> validateCartItem(CartItem cartItem) {
        if (cartItem == null) {
            return this.buildResponse(HttpStatus.BAD_REQUEST, "",
                    "Cart's item is empty.", null);
        }

        if (cartItem.getQuantity() <= 0) {
            return this.buildResponse(HttpStatus.BAD_REQUEST, "",
                    "Invalid item quantity value.", null);
        }

        if (cartItem.getProductId() <= 0) {
            return this.buildResponse(HttpStatus.BAD_REQUEST, "",
                    "Invalid Cart-item's product.", null);
        }

        if (productService.getProductById(cartItem.getProductId()) == null) {
            return this.buildResponse(HttpStatus.BAD_REQUEST, "",
                    "Cart-item's product doesn't exist.", null);
        }
        return null;
    }
}