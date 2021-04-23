package tn.esprit.pidev.consommitounsi.repositories.event;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import tn.esprit.pidev.consommitounsi.entities.events.Donation;
import tn.esprit.pidev.consommitounsi.entities.events.Event;
import tn.esprit.pidev.consommitounsi.entities.events.Ticket;

import java.util.List;

public interface TicketRepository extends CrudRepository<Ticket, Long> {

    @Query("select t from Ticket t where t.cagnotte.id = :cagnotteId ")
    List<Ticket> getTicketByCagnotte(@Param("cagnotteId") long cagnotteId);


    @Query("select t from Ticket t where t.user.id= :userId ")
    List<Ticket> getTicketById(@Param("userId") long userId);

}
