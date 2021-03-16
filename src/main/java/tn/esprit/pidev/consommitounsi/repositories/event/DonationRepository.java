package tn.esprit.pidev.consommitounsi.repositories.event;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.pidev.consommitounsi.entities.common.Address;
import tn.esprit.pidev.consommitounsi.entities.events.Donation;

@Repository
public interface DonationRepository extends CrudRepository<Donation, Long> {
}
