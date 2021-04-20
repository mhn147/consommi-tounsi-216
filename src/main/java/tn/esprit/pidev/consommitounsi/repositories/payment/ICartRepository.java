package tn.esprit.pidev.consommitounsi.repositories.payment;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import tn.esprit.pidev.consommitounsi.entities.payment.Cart;
import tn.esprit.pidev.consommitounsi.entities.payment.Order;

public interface ICartRepository extends CrudRepository<Cart, Long> {
    @Query("SELECT c FROM Cart c WHERE c.user.id= :userId")
    Cart getCartByUserId(@Param("userId")long userId);
}
