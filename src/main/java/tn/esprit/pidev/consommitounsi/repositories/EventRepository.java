package tn.esprit.pidev.consommitounsi.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.pidev.consommitounsi.entities.common.Address;
import tn.esprit.pidev.consommitounsi.entities.events.Event;

@Repository
public interface EventRepository extends CrudRepository<Event, Long> {
}
