package tn.esprit.pidev.consommitounsi.services.event;

import tn.esprit.pidev.consommitounsi.entities.events.Event;

import java.util.List;

public interface IEventService {


     Event getEventById(long id);
     List<Event> getAllEvents();
     void saveOrUpdateEvents(Event event);
     void DeleteEvent(long id);

}
