package tn.esprit.pidev.consommitounsi.controllers.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidev.consommitounsi.entities.payment.Item;
import tn.esprit.pidev.consommitounsi.services.payment.ItemService;

import java.util.List;

@RestController
@RequestMapping(path = "items")
public class ItemController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public List<Item> getItems() {
        return this.itemService.getAll();
    }

    @GetMapping(path = "{itemId}")
    public Item get(@PathVariable("itemId") Long itemId) {
        return this.itemService.getById(itemId);
    }

    @PostMapping
    public Item add(@RequestBody Item item) {
        return this.itemService.addOrUpdate(item);
    }

    @DeleteMapping(path = "{itemId}")
    public void remove(@PathVariable("itemId") Long itemId) {
        this.itemService.remove(itemId);
    }

    @PutMapping(path = "{itemId}")
    public void update(@PathVariable("itemId") Long itemId,
                       @RequestBody Item item) {
        item.setId(itemId);
        this.itemService.addOrUpdate(item);
    }
}
