package tn.esprit.pidev.consommitounsi.repositories.payment;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import tn.esprit.pidev.consommitounsi.entities.payment.Order;
import tn.esprit.pidev.consommitounsi.entities.payment.OrderStatus;

public interface IOrderRepository extends CrudRepository<Order, Long> {
    @Query("SELECT o FROM Order o WHERE o.status= :orderStatus AND o.user.id= :userId") // status == 'OrderStatus.CART'
    Order getCartByUserId(@Param("orderStatus")OrderStatus orderStatus,
                          @Param("userId")long userId);
}
