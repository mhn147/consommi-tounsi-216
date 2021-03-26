package tn.esprit.pidev.consommitounsi.repositories.event;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import tn.esprit.pidev.consommitounsi.entities.events.Donation;
import tn.esprit.pidev.consommitounsi.entities.events.Event;
import tn.esprit.pidev.consommitounsi.entities.events.Ticket;

import java.util.List;

public interface TicketRepository extends CrudRepository<Ticket, Long> {


}
