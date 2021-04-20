package tn.esprit.pidev.consommitounsi.services.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pidev.consommitounsi.entities.payment.Item;
import tn.esprit.pidev.consommitounsi.repositories.payment.IItemRepository;
import tn.esprit.pidev.consommitounsi.services.payment.interfaces.IItemService;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemService implements IItemService {

    private final IItemRepository itemRepository;

    @Autowired
    public ItemService(IItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public List<Item> getAll() {
        return (List<Item>)itemRepository.findAll();
    }

    @Override
    public Item getById(Long id) {
        return this.itemRepository.findById(id).orElse(null);
    }

    @Override
    public Item addOrUpdate(Item item) {
        return this.itemRepository.save(item);
    }



    @Override
    public List<Item> addAll(List<Item> items) {
        List<Item> newItems = new ArrayList<>();

        for (Item item:
             items) {
            newItems.add(this.itemRepository.save(new Item(item.getQuantity(), item.getProduct())));
        }

        return newItems;
    }

    @Override
    public void remove(Long id) {
        Item item = this.getById(id);
        itemRepository.delete(item);
    }
}
