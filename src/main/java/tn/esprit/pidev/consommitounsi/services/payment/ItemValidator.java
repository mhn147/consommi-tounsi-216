package tn.esprit.pidev.consommitounsi.services.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pidev.consommitounsi.entities.payment.Item;
import tn.esprit.pidev.consommitounsi.models.payment.ValidationResult;
import tn.esprit.pidev.consommitounsi.services.payment.interfaces.IEntityValidator;
import tn.esprit.pidev.consommitounsi.services.payment.interfaces.IItemService;
import tn.esprit.pidev.consommitounsi.services.products.ProductService;

@Service
public class ItemValidator implements IEntityValidator<Item> {

    private final ProductService productService;
    private final IItemService itemService;

    @Autowired
    public ItemValidator(ProductService productService,
                         IItemService itemService) {
        this.productService = productService;
        this.itemService = itemService;
    }

    @Override
    public ValidationResult validateExistence(Long id) {
        ValidationResult result = new ValidationResult();

        Item item = this.itemService.getById(id);
        if (item == null) {
            result.setValid(false);
            result.setValidationError("Item doesn't exist.");
        } else {
            result.setValid(true);
        }

        return result;
    }

    @Override
    public ValidationResult validateInput(Item item) {
        ValidationResult result = new ValidationResult();

        if (item == null) {
            result.setValid(false);
            result.setValidationError("Item is empty.");
        } else if (item.getQuantity() <= 0) {
            result.setValid(false);
            result.setValidationError("Provided Item's quantity is invalid");
        } else if (item.getProduct() == null) {
            result.setValid(false);
            result.setValidationError("Item does not have an associated product.");
        } else if (this.productService.getProductById(item.getProduct().getId()) == null) {
            result.setValid(false);
            result.setValidationError("Item's associated product does not exist");
        } else {
            result.setValid(true);
        }

        return result;
    }
}
