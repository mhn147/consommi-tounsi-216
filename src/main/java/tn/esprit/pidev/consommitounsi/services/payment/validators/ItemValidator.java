package tn.esprit.pidev.consommitounsi.services.payment.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pidev.consommitounsi.entities.payment.Item;
import tn.esprit.pidev.consommitounsi.models.payment.ValidationResult;
import tn.esprit.pidev.consommitounsi.services.payment.interfaces.IItemService;
import tn.esprit.pidev.consommitounsi.services.products.ProductService;

@Service
public class ItemValidator {

    private final ProductService productService;
    private final IItemService itemService;

    private ValidationResult validationResult = new ValidationResult();

    @Autowired
    public ItemValidator(ProductService productService,
                         IItemService itemService) {
        this.productService = productService;
        this.itemService = itemService;
    }

    public ValidationResult validateExistence(Long itemId) {
        this.reset();

        Item item = this.itemService.getById(itemId);
        if (item == null) {
            this.validationResult.setValidationError("Item doesn't exist.");
        } else {
            this.validationResult.setValid(true);
        }

        return this.validationResult;
    }

    public ValidationResult validateAdd(Item item) {
        reset();

        if (item == null) {
            this.validationResult.setValidationError("Item is empty.");
        } else if (item.getQuantity() <= 0) {
            this.validationResult.setValidationError("Provided Item's quantity is invalid");
        } else if (item.getProduct() == null) {
            this.validationResult.setValidationError("Item does not have an associated product.");
        } else if (this.productService.getProductById(item.getProduct().getId()) == null) {
            this.validationResult.setValidationError("Item's associated product does not exist");
        } else {
            this.validationResult.setValid(true);
        }

        return this.validationResult;
    }

    private void reset() {
        this.validationResult.setValid(false);
        this.validationResult.setValidationError(null);
    }
}
