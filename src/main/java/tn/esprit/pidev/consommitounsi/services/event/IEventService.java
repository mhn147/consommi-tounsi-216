package tn.esprit.pidev.consommitounsi.services.event;

import tn.esprit.pidev.consommitounsi.entities.events.Event;
import tn.esprit.pidev.consommitounsi.entities.user.User;

import java.text.ParseException;
import java.util.List;

public interface IEventService {


     Event getEventById(long id);
     List<Event> getAllEvents();
     List<Event> getLastEvents();
     List<Event> getSelectedEvents(long userId) throws ParseException;
     void saveOrUpdateEvents(Event event);
     void DeleteEvent(long id);
     void  RefreshEvent();
     User getWinner();
}
