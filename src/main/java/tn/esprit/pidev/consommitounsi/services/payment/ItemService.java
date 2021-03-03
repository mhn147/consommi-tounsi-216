package tn.esprit.pidev.consommitounsi.services.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pidev.consommitounsi.entities.payment.Invoice;
import tn.esprit.pidev.consommitounsi.entities.payment.Item;
import tn.esprit.pidev.consommitounsi.repositories.payment.IItemRepository;
import tn.esprit.pidev.consommitounsi.services.common.IService;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ItemService implements IService<Item>, IItemService {

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
        return this.itemRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("Item with id " + id + " does not exist.")
        );
    }

    @Override
    public Item add(Item item) {
        return this.itemRepository.save(item);
    }

    @Override
    @Transactional
    public Item update(Item item, Long id) {
        Item oldItem = this.getById(id);

        // TODO Validation
        oldItem = item;

        return item;
    }

    @Override
    public void remove(Long id) {
        Item item = this.getById(id);
        itemRepository.delete(item);
    }
}
