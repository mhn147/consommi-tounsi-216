package tn.esprit.pidev.consommitounsi.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.pidev.consommitounsi.entities.common.Address;

import java.util.List;

@Repository
public interface AddressRepository extends CrudRepository<Address, Long> {
    @Query("select a from Address a where a.user.id = :userId")
    List<Address> getUserAddresses(@Param("userId")long userId);
}
