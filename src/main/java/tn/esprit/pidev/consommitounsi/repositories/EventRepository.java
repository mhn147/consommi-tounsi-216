package tn.esprit.pidev.consommitounsi.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.pidev.consommitounsi.entities.common.Address;
import tn.esprit.pidev.consommitounsi.entities.events.Event;

import java.util.List;

@Repository
public interface EventRepository extends CrudRepository<Event, Long> {
    @Query("select e from Event e where  e.eventType='SPORT' ")
    List<Event> getSportEvent();
    @Query("select e from Event e where  e.eventType='CULTURE' ")
    List<Event> getCultureEvent();
    @Query("select e from Event e where  e.eventType='MEETINGS' ")
    List<Event> getMeetingsEvent();
    @Query("select e from Event e where  e.eventType='BUSINESS' ")
    List<Event> getBusinessEvent();
    @Query("select e from Event e where  e.eventType='CHARITY' ")
    List<Event> getCharityEvent();
    @Query("select e from Event e where  e.eventType='COOKING' ")
    List<Event> getCookingEvent();

    @Query("select e from Event e order by  e.eventDate DESC ")
    List<Event> getLastEvent();


}
