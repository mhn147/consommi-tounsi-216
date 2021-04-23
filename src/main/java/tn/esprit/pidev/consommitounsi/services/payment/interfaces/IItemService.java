package tn.esprit.pidev.consommitounsi.services.payment.interfaces;

import tn.esprit.pidev.consommitounsi.entities.payment.Item;

import java.util.List;

public interface IItemService {
    List<Item> getAll();
    Item getById(Long id);
    Item addOrUpdate(Item entity);
    void remove(Long id);
}
