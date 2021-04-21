package tn.esprit.pidev.consommitounsi.repositories.payment;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.pidev.consommitounsi.entities.payment.Order;
import tn.esprit.pidev.consommitounsi.entities.payment.OrderStatus;

@Repository
public interface IOrderRepository extends CrudRepository<Order, Long> {
}
