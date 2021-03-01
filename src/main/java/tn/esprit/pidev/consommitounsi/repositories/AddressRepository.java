package tn.esprit.pidev.consommitounsi.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.pidev.consommitounsi.entities.common.Address;

@Repository
public interface AddressRepository extends CrudRepository<Address, Long> {
}
