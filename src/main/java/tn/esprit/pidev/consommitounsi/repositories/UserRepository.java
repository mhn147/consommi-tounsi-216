package tn.esprit.pidev.consommitounsi.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.pidev.consommitounsi.entities.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.username = :username Or u.email = :username")
    User getUserByUsernameOrEmail(@Param("username")String username);
}
