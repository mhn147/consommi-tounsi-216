package tn.esprit.pidev.consommitounsi.repositories.delivery;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.pidev.consommitounsi.entities.User;

@Repository
public interface DeliveryRepository extends CrudRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.type = :username Or u.email = :username")
    User getUserByUsernameOrEmail(@Param("username")String username);
    @Query("SELECT u FROM User u WHERE u.type = :type")
    User getUserByUserType(@Param("type")String username);
}
