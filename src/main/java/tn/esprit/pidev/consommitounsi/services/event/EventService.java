package tn.esprit.pidev.consommitounsi.services.event;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import tn.esprit.pidev.consommitounsi.entities.events.Event;
import tn.esprit.pidev.consommitounsi.entities.events.Ticket;
import tn.esprit.pidev.consommitounsi.entities.user.CivilState;
import tn.esprit.pidev.consommitounsi.entities.user.Gender;
import tn.esprit.pidev.consommitounsi.entities.user.Occupation;
import tn.esprit.pidev.consommitounsi.entities.user.User;
import tn.esprit.pidev.consommitounsi.repositories.event.EventRepository;
import tn.esprit.pidev.consommitounsi.repositories.user.AddressRepository;
import tn.esprit.pidev.consommitounsi.repositories.user.UserRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

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
    public List<Event> getDisponibleEvent() {
        return (List<Event>)eventRepository.getDisponibleEvent();
    }


    @Override
    public List<Event> getSelectedEvents(long userId) throws ParseException {

        User user = userRepository.findById(userId).orElse(null);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date quarante = sdf.parse("1980-01-01 00:00:00");
        Date majeur = sdf.parse("2001-01-01 00:00:00");
        Date soixante = sdf.parse("1960-01-10 00:00:00");



        if(( user.getCivilState()==CivilState.SINGLE&& user.getGender()==Gender.MALE && user.getOccupation()==Occupation.STUDENT) || user.getOccupation()==Occupation.RETIRED){
            return  (List<Event>)eventRepository.getSportEvent();
        }
        else if ((user.getCivilState()==CivilState.SINGLE&& user.getBirthDate().after(quarante) && user.getBirthDate().before(soixante)) ||user.getOccupation()==Occupation.BUSINESS_MAN ){
            return  (List<Event>)eventRepository.getBusinessEvent();
        }
        else if ( user.getCivilState()==CivilState.SINGLE && user.getGender()==Gender.FEMALE && user.getOccupation()==Occupation.STUDENT){
            return  (List<Event>)eventRepository.getCultureEvent();
        }

        else if ( user.getCivilState()==CivilState.MARRIED && user.getGender()== Gender.FEMALE && user.getBirthDate().after(majeur) && user.getBirthDate().before(quarante)){
            return  (List<Event>)eventRepository.getCookingEvent();
        }

        else if( user.getCivilState()==CivilState.DIVORCED&& user.getOccupation()==Occupation.BUSINESS_MAN){
            return  (List<Event>)eventRepository.getCharityEvent();
        }
        else if ( user.getCivilState()==CivilState.DIVORCED&& user.getOccupation()==Occupation.ARTIST){
            return  (List<Event>)eventRepository.getCultureEvent();
        }

        else if (user.getCivilState()==CivilState.DIVORCED&& user.getOccupation()==Occupation.SOCIAL_WORKER){
            return (List<Event>)eventRepository.getCharityEvent();
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


    }





