package tn.esprit.pidev.consommitounsi.controllers.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidev.consommitounsi.controllers.payment.helpers.IResponseBuilder;
import tn.esprit.pidev.consommitounsi.controllers.payment.helpers.ItemResponseBuilder;
import tn.esprit.pidev.consommitounsi.models.payment.ResponseModel;
import tn.esprit.pidev.consommitounsi.entities.payment.Item;
import tn.esprit.pidev.consommitounsi.models.payment.ValidationResult;
import tn.esprit.pidev.consommitounsi.services.payment.ItemValidator;
import tn.esprit.pidev.consommitounsi.services.payment.interfaces.IEntityInputValidator;
import tn.esprit.pidev.consommitounsi.services.payment.interfaces.IItemService;

import java.util.List;

@RestController
@RequestMapping
public class ItemController {

    private final IItemService itemService;
    private final IEntityInputValidator itemValidator;
    private final IResponseBuilder<Item> responseBuilder;

    @Autowired
    public ItemController(IItemService itemService,
                          ItemValidator itemValidator,
                          ItemResponseBuilder responseBuilder) {
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
        Item item = this.itemService.getById(itemId);

        if (item == null) {
            return this.responseBuilder.notFoundResponse();
        }
        return this.responseBuilder.okResponse(item, null);
    }

    @PostMapping(path = "items")
    public ResponseEntity<ResponseModel<Item>> add(@RequestBody Item item) {
        ValidationResult validationResult = this.itemValidator.validate(item);
        if (!validationResult.isValid()) {
            return this.responseBuilder.badRequestResponse(validationResult.getValidationError());
        }
        Item createdItem = this.itemService.addOrUpdate(item);
        return this.responseBuilder.createdResponse(createdItem);
    }

    @DeleteMapping(path = "items/{itemId}")
    public ResponseEntity<ResponseModel<Item>> delete(@PathVariable("itemId") Long itemId) {
        Item item = this.itemService.getById(itemId);

        if (item == null) {
            return this.responseBuilder.notFoundResponse();
        }

        this.itemService.remove(itemId);
        return this.responseBuilder.okResponse(item, "Item deleted with success.");
    }

    @PutMapping(path = "items/{itemId}")
    public ResponseEntity<ResponseModel<Item>> update(@PathVariable("itemId") Long itemId,
                       @RequestBody Item item) {
        Item actualItem = this.itemService.getById(itemId);

        if (actualItem == null) {
            return this.responseBuilder.notFoundResponse();
        }
        item.setId(itemId);
        ValidationResult validationResult = this.itemValidator.validate(item);
        if (!validationResult.isValid()) {
            return this.responseBuilder.badRequestResponse(validationResult.getValidationError());
        }

        Item updatedItem = this.itemService.addOrUpdate(item);
        return this.responseBuilder.createdResponse(updatedItem);
    }
}
