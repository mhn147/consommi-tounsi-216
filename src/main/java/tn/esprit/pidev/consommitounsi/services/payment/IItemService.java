package tn.esprit.pidev.consommitounsi.services.payment;

import tn.esprit.pidev.consommitounsi.entities.payment.Item;
import tn.esprit.pidev.consommitounsi.services.common.IService;

import java.util.List;

public interface IItemService extends IService<Item> {
    Item updateItemQuantity(Item item, int quantity);
    List<Item> addAll(List<Item> items);
}
