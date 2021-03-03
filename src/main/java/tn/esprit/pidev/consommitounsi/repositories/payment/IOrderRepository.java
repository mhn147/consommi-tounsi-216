package tn.esprit.pidev.consommitounsi.repositories.payment;

import org.springframework.data.repository.CrudRepository;
import tn.esprit.pidev.consommitounsi.entities.payment.Order;

public interface IOrderRepository extends CrudRepository<Order, Long> {
}
