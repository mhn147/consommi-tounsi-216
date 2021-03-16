package tn.esprit.pidev.consommitounsi.repositories.event;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;
import tn.esprit.pidev.consommitounsi.entities.common.Address;
import tn.esprit.pidev.consommitounsi.entities.events.Event;

import javax.transaction.Transactional;
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

    @Scheduled(fixedRate = 1000L)
    @Transactional
    @Modifying
    @Query("delete from Event e where e.eventDate <= current_date OR e.sumCollect=e.maxCollect ")
    void RefreshEvent();



}
