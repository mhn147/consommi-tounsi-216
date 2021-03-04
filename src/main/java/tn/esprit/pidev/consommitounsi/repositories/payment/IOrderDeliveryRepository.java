package tn.esprit.pidev.consommitounsi.repositories.payment;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.pidev.consommitounsi.entities.payment.OrderDelivery;

@Repository
public interface IOrderDeliveryRepository extends CrudRepository<OrderDelivery, Long> {
}
