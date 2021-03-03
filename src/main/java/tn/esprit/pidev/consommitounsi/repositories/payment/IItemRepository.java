package tn.esprit.pidev.consommitounsi.repositories.payment;

import org.springframework.data.repository.CrudRepository;
import tn.esprit.pidev.consommitounsi.entities.payment.Item;

public interface IItemRepository extends CrudRepository<Item, Long> {
}
