package tn.esprit.pidev.consommitounsi.services.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pidev.consommitounsi.entities.events.Event;
import tn.esprit.pidev.consommitounsi.repositories.EventRepository;

import java.util.List;


@Service
public class EventService implements IEventService {


    @Autowired
    EventRepository eventRepository;


    @Override
    public Event getEventById(long id) {
        return eventRepository.findById(id).orElse(null);
    }

    @Override
    public List<Event> getAllEvents() {
        return (List<Event>)eventRepository.findAll();
    }

    @Override
    public void saveOrUpdateEvents(Event event) {
        eventRepository.save(event);
    }

    @Override
    public void DeleteEvent(long id) {
    eventRepository.deleteById(id);
    }
}
