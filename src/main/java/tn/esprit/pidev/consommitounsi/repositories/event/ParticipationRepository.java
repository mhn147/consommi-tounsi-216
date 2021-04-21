package tn.esprit.pidev.consommitounsi.repositories.event;

import org.springframework.data.repository.CrudRepository;
import tn.esprit.pidev.consommitounsi.entities.events.Participation;
import tn.esprit.pidev.consommitounsi.entities.events.Ticket;

public interface ParticipationRepository extends CrudRepository<Participation, Long> {
}
