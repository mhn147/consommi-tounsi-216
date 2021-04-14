package tn.esprit.pidev.consommitounsi.services.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import tn.esprit.pidev.consommitounsi.entities.payment.Cart;
import tn.esprit.pidev.consommitounsi.entities.payment.Item;
import tn.esprit.pidev.consommitounsi.entities.user.User;
import tn.esprit.pidev.consommitounsi.models.payment.ValidationResult;
import tn.esprit.pidev.consommitounsi.services.payment.interfaces.ICartService;
import tn.esprit.pidev.consommitounsi.services.payment.interfaces.IEntityValidator;
import tn.esprit.pidev.consommitounsi.services.user.IUserService;

@Service
public class CartValidator implements IEntityValidator<Cart> {

    private final IUserService userService;
    private final ICartService cartService;

    @Autowired
    public CartValidator(IUserService userService,
                          ICartService cartService) {
        this.userService = userService;
        this.cartService = cartService;
    }

    @Override
    public ValidationResult validateExistence(Long userId) {
        ValidationResult result = new ValidationResult();

            User user = this.userService.getById(userId);

            if (user == null) {
                result.setValid(false);
                result.setValidationError("User doesn't exist");
            } else if (this.cartService.getByUserId(userId) == null) {
                result.setValid(false);
                result.setValidationError("User doesn't have a cart");
            } else {
                result.setValid(true);
            }
        return result;
    }

    @Override
    public ValidationResult validateInput(Cart cart) {
        // TODO
        throw new NotImplementedException();
    }
}
