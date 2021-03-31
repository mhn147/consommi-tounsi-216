package tn.esprit.pidev.consommitounsi.services.payment;

import tn.esprit.pidev.consommitounsi.entities.payment.Item;
import tn.esprit.pidev.consommitounsi.services.common.IService;

public interface IItemService extends IService<Item> {
    Item updateItemQuantity(Item item, int quantity);
}
