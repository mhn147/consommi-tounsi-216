package tn.esprit.pidev.consommitounsi.controllers.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidev.consommitounsi.controllers.payment.helpers.IResponseBuilder;
import tn.esprit.pidev.consommitounsi.controllers.payment.helpers.ResponseBuilder;
import tn.esprit.pidev.consommitounsi.entities.payment.Cart;
import tn.esprit.pidev.consommitounsi.entities.payment.Item;
import tn.esprit.pidev.consommitounsi.entities.products.Product;
import tn.esprit.pidev.consommitounsi.models.payment.ResponseModel;
import tn.esprit.pidev.consommitounsi.models.payment.ValidationResult;
import tn.esprit.pidev.consommitounsi.services.payment.interfaces.ICartService;
import tn.esprit.pidev.consommitounsi.services.payment.interfaces.IItemService;
import tn.esprit.pidev.consommitounsi.services.payment.validators.CartValidator;
import tn.esprit.pidev.consommitounsi.services.payment.validators.ItemValidator;
import tn.esprit.pidev.consommitounsi.services.products.ProductService;
import tn.esprit.pidev.consommitounsi.services.user.IUserService;

@RestController
@RequestMapping
public class CartController {

    private final IUserService userService;
    private final ICartService cartService;
    private final IItemService itemService;
    private final IResponseBuilder<Cart> responseBuilder;
    private final CartValidator cartValidator;
    private final ItemValidator itemValidator;
    private final ProductService productService;

    @Autowired
    public CartController(IUserService userService,
                          ICartService cartService,
                          IItemService itemService,
                          ResponseBuilder<Cart> responseBuilder,
                          CartValidator cartValidator,
                          ItemValidator itemValidator,
                          ProductService productService) {
        this.userService = userService;
        this.cartService = cartService;
        this.itemService = itemService;
        this.responseBuilder = responseBuilder;
        this.cartValidator = cartValidator;
        this.itemValidator = itemValidator;
        this.productService = productService;
    }

    @GetMapping(path = "users/{userId}/cart")
    public ResponseEntity<ResponseModel<Cart>> get(@PathVariable("userId") Long userId) {

        ValidationResult validationResult = this.cartValidator.validateExistence(userId);
        if (!validationResult.isValid()) {
            return this.responseBuilder.badRequestResponse(
                    validationResult.getValidationError());
        }

        Cart userCart = this.cartService.getByUserId(userId);
        return this.responseBuilder.okResponse(userCart, null);
    }

//    @PostMapping(path = "users/{userId}/cart/items")
//    public ResponseEntity<ResponseModel<Cart>> addItem(@PathVariable("userId") Long userId,
//                                                       @RequestBody Item item) {
//
//        ValidationResult validationResult = this.cartValidator.validateExistence(userId);
//        if (!validationResult.isValid()) {
//            return this.responseBuilder.badRequestResponse(
//                    validationResult.getValidationError());
//        }
//
//        validationResult = this.itemValidator.validateAdd(item);
//        if (!validationResult.isValid()) {
//            return this.responseBuilder.badRequestResponse(
//                    validationResult.getValidationError());
//        }
//
//        Cart userCart = this.cartService.getByUserId(userId);
//
//        validationResult = this.cartValidator.validateNoDuplicateItemProduct(userCart, item.getProduct().getId());
//        if (!validationResult.isValid()) {
//            return this.responseBuilder.badRequestResponse(validationResult.getValidationError());
//        }
//
//        Item createdItem = this.itemService.addOrUpdate(item);
//        userCart = this.cartService.addItem(userCart.getId(), createdItem);
//        return this.responseBuilder.okResponse(userCart, "Item added to user's cart.");
//    }

    @PostMapping(path = "users/carts/{cartId}/items/{productId}")
    public ResponseEntity<ResponseModel<Cart>> addItem(@PathVariable("cartId") Long cartId,
                                                        @PathVariable("productId") Long productId,
                                                       @RequestBody Item item) {



        Cart userCart = this.cartService.get(cartId);

        if (userCart == null) {
            return this.responseBuilder.badRequestResponse("Invalid Cart Id.");
        }

        ValidationResult validationResult =
                this.cartValidator.validateNoDuplicateItemProduct(userCart, productId);
        if (!validationResult.isValid()) {
            return this.responseBuilder.badRequestResponse(validationResult.getValidationError());
        }

        Product product = this.productService.getProductById(productId);
        item.setProduct(product);
        Item createdItem = this.itemService.addOrUpdate(item);
        userCart = this.cartService.addItem(userCart.getId(), createdItem);
        return this.responseBuilder.okResponse(userCart, "Item added to user's cart.");
    }

    @DeleteMapping(path = "users/carts/{cartId}/items/{itemId}")
    public ResponseEntity<ResponseModel<Cart>> deleteItem(@PathVariable("cartId") Long cartId,
                                                          @PathVariable("itemId") Long itemId) {

        ValidationResult validationResult = this.itemValidator.validateExistence(itemId);
        if (!validationResult.isValid()) {
            return this.responseBuilder.badRequestResponse(validationResult.getValidationError());
        }

        Cart newCart = this.cartService.removeItem(cartId, itemId);
        return this.responseBuilder.okResponse(newCart, "Item deleted.");
    }

    @PatchMapping(path = "users/{userId}/cart/items/{itemId}")
    public ResponseEntity<ResponseModel<Cart>> updateItemQuantity(@PathVariable("userId") Long userId,
                                                                  @PathVariable("itemId") Long itemId,
                                                                  @RequestBody int quantity) {
        if (quantity <= 0) {
            return this.responseBuilder.badRequestResponse("Invalid quantity value");
        }

        ResponseEntity<ResponseModel<Cart>> validateExistence = this.validateExistence(userId, itemId);
        if (validateExistence != null) {
            return validateExistence;
        }

        Item item = this.itemService.getById(itemId);
        item.setId(itemId);
        item.setQuantity(quantity);

        this.itemService.addOrUpdate(item);
        Cart updatedCart = this.cartService.getByUserId(userId);
        return this.responseBuilder.createdResponse(updatedCart, "Item updated.");
    }

    private ResponseEntity<ResponseModel<Cart>> validateExistence(
            @PathVariable("userId") Long userId,
            @PathVariable("itemId") Long itemId) {

        ValidationResult validationResult = this.cartValidator.validateExistence(userId);
        if (!validationResult.isValid()) {
            return this.responseBuilder.badRequestResponse(validationResult.getValidationError());
        }

        validationResult = this.itemValidator.validateExistence(itemId);
        if (!validationResult.isValid()) {
            return this.responseBuilder.badRequestResponse(validationResult.getValidationError());
        }

        return null;
    }

}