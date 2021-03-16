package tn.esprit.pidev.consommitounsi.services.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.pidev.consommitounsi.entities.common.Address;
import tn.esprit.pidev.consommitounsi.entities.events.Event;
import tn.esprit.pidev.consommitounsi.entities.user.Gender;
import tn.esprit.pidev.consommitounsi.entities.user.User;
import tn.esprit.pidev.consommitounsi.repositories.event.EventRepository;
import tn.esprit.pidev.consommitounsi.repositories.user.AddressRepository;
import tn.esprit.pidev.consommitounsi.repositories.user.UserRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Service
public class EventService implements IEventService {


    @Autowired
    EventRepository eventRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    AddressRepository addressRepository;


    @Override
    public Event getEventById(long id) {
        return eventRepository.findById(id).orElse(null);
    }

    @Override
    public List<Event> getAllEvents() {
        return (List<Event>)eventRepository.findAll();
    }

    @Override
    public List<Event> getLastEvents() {
        return (List<Event>)eventRepository.getLastEvent();
    }

    @Override
    public List<Event> getSelectedEvents(long userId) throws ParseException {
        User user = userRepository.findById(userId).orElse(null);
        Address adresse = addressRepository.findById(userId).orElse(null);


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //
        Date jeune = sdf.parse("1980-01-01 00:00:00");
        Date girls = sdf.parse("2001-01-01 00:00:00");
        Date charity = sdf.parse("1975-01-01 00:00:00");
        Date culture2 = sdf.parse("2001-01-10 00:00:00");

        // sport age -40//

        if(user.getGender()== Gender.MALE && user.getBirthDate().after(jeune) ){
            return  (List<Event>)eventRepository.getSportEvent();

        }
         //  +45 //
         else if(user.getBirthDate().before(charity) ){
            return  (List<Event>)eventRepository.getBusinessEvent();
        }
        //  girls cooking +18 ans inf 45 ans //
        else if(user.getGender()== Gender.FEMALE && user.getBirthDate().after(charity) && user.getBirthDate().before(girls) ) {

            return (List<Event>) eventRepository.getCookingEvent();
        }
        // plus 45 //
        else if( user.getBirthDate().before(charity)) {

            return (List<Event>) eventRepository.getCharityEvent();
        }

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

    @Scheduled(fixedRate = 1000000L)
    @Override
    public void RefreshEvent() {
        eventRepository.RefreshEvent();

    }

    @Override
    public User getWinner( ) {

        long sum = userRepository.count();
        long leftLimit = 1L;
        long rightLimit = sum + 1L;

            long generatedLong = leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
            User user = userRepository.findById(generatedLong).orElse(null);

        return user;
    }

}

