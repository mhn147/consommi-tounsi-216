package tn.esprit.pidev.consommitounsi.services.event;

import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pidev.consommitounsi.entities.common.Address;
import tn.esprit.pidev.consommitounsi.entities.events.Event;
import tn.esprit.pidev.consommitounsi.entities.user.Gender;
import tn.esprit.pidev.consommitounsi.entities.user.User;
import tn.esprit.pidev.consommitounsi.repositories.EventRepository;
import tn.esprit.pidev.consommitounsi.repositories.user.AddressRepository;
import tn.esprit.pidev.consommitounsi.repositories.user.UserRepository;

import java.text.DateFormat;
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
    public List<Event> getSelectedEvents(long userId) throws ParseException {
        User user = userRepository.findById(userId).orElse(null);
        Address adresse = addressRepository.findById(userId).orElse(null);


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //
        Date jeune = sdf.parse("1980-10-10 00:00:00");
        Date girls = sdf.parse("2001-10-10 00:00:00");
        // sport age -40//

        if(user.getGender()== Gender.MALE && user.getBirthDate().after(jeune) ){
            return  (List<Event>)eventRepository.getSportEvent();
        }
         //  +18 //
         else if(user.getBirthDate().before(girls)){
            return  (List<Event>)eventRepository.getCultureEvent();
        }
        //  girls cooking +18 //
        else if(user.getGender()== Gender.FEMALE && user.getBirthDate().before(girls) ) {

            return (List<Event>) eventRepository.getCookingEvent();
        }



        else return null;
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
