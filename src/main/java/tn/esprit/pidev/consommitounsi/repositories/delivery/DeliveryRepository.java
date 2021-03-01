package tn.esprit.pidev.consommitounsi.repositories.delivery;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import tn.esprit.pidev.consommitounsi.entities.User;

public interface DeliveryRepository extends CrudRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.username = :type ")
    User getUserByUserType(@Param("username")String type);
}
