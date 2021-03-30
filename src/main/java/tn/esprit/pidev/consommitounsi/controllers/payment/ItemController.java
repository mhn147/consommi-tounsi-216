package tn.esprit.pidev.consommitounsi.controllers.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidev.consommitounsi.entities.payment.Item;
import tn.esprit.pidev.consommitounsi.services.payment.ItemService;

import java.util.List;

@RestController
@RequestMapping
public class ItemController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping(path = "items")
    public List<Item> getItems() {
        return this.itemService.getAll();
    }

    @GetMapping(path = "items/{itemId}")
    public Item get(@PathVariable("itemId") Long itemId) {
        return this.itemService.getById(itemId);
    }

    @PostMapping(path = "items")
    public Item add(@RequestBody Item item) {
        return this.itemService.addOrUpdate(item);
    }

    @DeleteMapping(path = "items/{itemId}")
    public void remove(@PathVariable("itemId") Long itemId) {
        this.itemService.remove(itemId);
    }

    @PutMapping(path = "items/{itemId}")
    public void update(@PathVariable("itemId") Long itemId,
                       @RequestBody Item item) {
        item.setId(itemId);
        this.itemService.addOrUpdate(item);
    }
}
