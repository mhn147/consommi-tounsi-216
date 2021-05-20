package tn.esprit.pidev.consommitounsi.controllers.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidev.consommitounsi.controllers.payment.helpers.IResponseBuilder;
import tn.esprit.pidev.consommitounsi.controllers.payment.helpers.ResponseBuilder;
import tn.esprit.pidev.consommitounsi.models.payment.ResponseModel;
import tn.esprit.pidev.consommitounsi.entities.payment.Item;
import tn.esprit.pidev.consommitounsi.models.payment.ValidationResult;
import tn.esprit.pidev.consommitounsi.services.payment.interfaces.IItemService;
import tn.esprit.pidev.consommitounsi.services.payment.validators.ItemValidator;

import java.util.List;

@RestController
@RequestMapping
public class ItemController {

    private final IItemService itemService;
    private final ItemValidator itemValidator;
    private final IResponseBuilder<Item> responseBuilder;

    @Autowired
    public ItemController(IItemService itemService,
                          ItemValidator itemValidator,
                          ResponseBuilder<Item> responseBuilder) {
        this.itemService = itemService;
        this.itemValidator = itemValidator;
        this.responseBuilder = responseBuilder;
    }

    @GetMapping(path = "items")
    public List<Item> getItems() {
            return this.itemService.getAll();
    }

    @GetMapping(path = "items/{itemId}")
    public ResponseEntity<ResponseModel<Item>> get(@PathVariable("itemId") Long itemId) {

        ValidationResult validationResult = this.itemValidator.validateExistence(itemId);
        if (!validationResult.isValid()) {
            return this.responseBuilder.badRequestResponse("Item don't exist.");
        }

        Item item = this.itemService.getById(itemId);
        return this.responseBuilder.okResponse(item, null);
    }

    @PostMapping(path = "items")
    public ResponseEntity<ResponseModel<Item>> add(@RequestBody Item item) {

        ValidationResult validationResult = this.itemValidator.validateAdd(item);
        if (!validationResult.isValid()) {
            return this.responseBuilder.badRequestResponse(
                    validationResult.getValidationError());
        }

        Item createdItem = this.itemService.addOrUpdate(item);
        return this.responseBuilder.createdResponse(createdItem, "Item created.");
    }

    @DeleteMapping(path = "items/{itemId}")
    public ResponseEntity<ResponseModel<Item>> delete(@PathVariable("itemId") Long itemId) {

        ValidationResult validationResult = this.itemValidator.validateExistence(itemId);
        if (!validationResult.isValid()) {
            return this.responseBuilder.badRequestResponse("Item don't exist.");
        }

        Item item = this.itemService.getById(itemId);
        this.itemService.remove(itemId);
        return this.responseBuilder.okResponse(item, "Item deleted.");
    }

    @PutMapping(path = "items/{itemId}/{quantity}")
    public ResponseEntity<ResponseModel<Item>> updateItemQuantity(@PathVariable("itemId") Long itemId,
                                                                  @PathVariable("quantity") Long quantity) {

        ValidationResult validationResult = this.itemValidator.validateExistence(itemId);
        if (!validationResult.isValid()) {
            return this.responseBuilder.badRequestResponse(validationResult.getValidationError());
        }

        Item item = this.itemService.getById(itemId);
        item.setQuantity(quantity.intValue());
        Item updatedItem = this.itemService.addOrUpdate(item);
        return this.responseBuilder.createdResponse(updatedItem, "Item updated.");
    }
}
