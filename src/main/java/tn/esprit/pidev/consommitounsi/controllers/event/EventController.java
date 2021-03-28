package tn.esprit.pidev.consommitounsi.controllers.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidev.consommitounsi.entities.chat.Chat;
import tn.esprit.pidev.consommitounsi.entities.events.Event;
import tn.esprit.pidev.consommitounsi.entities.forum.Topic;
import tn.esprit.pidev.consommitounsi.entities.user.User;
import tn.esprit.pidev.consommitounsi.services.event.EventService;

import java.text.ParseException;
import java.util.List;

@RestController
@EnableAutoConfiguration
public class EventController {
    @Autowired

    EventService eventService;

    @PostMapping("/AddEvent")
    public Event addEvent(@RequestBody Event event) {
        eventService.saveOrUpdateEvents(event);
        return event;
    }
    @GetMapping("/Events")
    @ResponseBody
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    @GetMapping("/LastEvents")
    @ResponseBody
    public List<Event> getLastEvents() {
        return eventService.getLastEvents();
    }

    @GetMapping("/event/{id}")
    @ResponseBody
    public Event getEventById(@PathVariable("id") long id) {
        return eventService.getEventById(id);
    }

    @GetMapping("/SelectedEvents/{id}")
    @ResponseBody
    public List<Event> getSelectedEvents(@PathVariable("id") long id) throws ParseException {
        return eventService.getSelectedEvents(id);
    }

    @DeleteMapping("/deleteEvent/{id}")
    @ResponseBody
    public void deleteEvent(@PathVariable("id")long id) {
        eventService.DeleteEvent(id);
    }

    @DeleteMapping("/refreshEvent")
    @ResponseBody
    public void refreshEvent() {
        eventService.RefreshEvent();

    }


}
