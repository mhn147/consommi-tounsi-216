package tn.esprit.pidev.consommitounsi.services.payment.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import tn.esprit.pidev.consommitounsi.entities.payment.Cart;
import tn.esprit.pidev.consommitounsi.entities.payment.Item;
import tn.esprit.pidev.consommitounsi.entities.user.User;
import tn.esprit.pidev.consommitounsi.models.payment.ValidationResult;
import tn.esprit.pidev.consommitounsi.repositories.payment.ICartRepository;
import tn.esprit.pidev.consommitounsi.services.payment.interfaces.ICartService;
import tn.esprit.pidev.consommitounsi.services.user.IUserService;

@Service
public class CartValidator {

    private final IUserService userService;
    private final ICartService cartService;
    private final ICartRepository cartRepository;
    private final ItemValidator itemValidator;


    private ValidationResult validationResult = new ValidationResult();

    @Autowired
    public CartValidator(IUserService userService,
                         ICartService cartService,
                         ICartRepository cartRepository,
                         ItemValidator itemValidator) {
        this.userService = userService;
        this.cartService = cartService;
        this.cartRepository = cartRepository;
        this.itemValidator = itemValidator;
    }

    public ValidationResult validateExistence(Long userId) {
        this.reset();

        User user = this.userService.getById(userId);
        if (user == null) {
            this.validationResult.setValidationError("User doesn't exist");
        } else if (this.cartService.getByUserId(userId) == null) {
            this.validationResult.setValidationError("User doesn't have a cart");
        } else {
            this.validationResult.setValid(true);
        }

        return this.validationResult;
    }

    public ValidationResult validateNoDuplicateItemProduct(Cart cart, Long productId) {
        this.reset();

        for (Item item : cart.getItems()) {
            if (item.getProduct().getId() == productId) {
                this.validationResult.setValidationError("The item contains a product that already exists in another item.");
                return this.validationResult;
            }
        }

        this.validationResult.setValid(true);
        return this.validationResult;
    }

    private void reset() {
        this.validationResult.setValid(false);
        this.validationResult.setValidationError(null);
    }
}
