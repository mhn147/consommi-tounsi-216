package tn.esprit.pidev.consommitounsi.services.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pidev.consommitounsi.entities.events.Event;
import tn.esprit.pidev.consommitounsi.entities.events.Participation;
import tn.esprit.pidev.consommitounsi.entities.user.User;
import tn.esprit.pidev.consommitounsi.repositories.event.EventRepository;
import tn.esprit.pidev.consommitounsi.repositories.event.ParticipationRepository;
import tn.esprit.pidev.consommitounsi.repositories.user.UserRepository;

import java.util.Date;
import java.util.List;

@Service
public class ParticipationService implements IParticipationService {

    @Autowired
    ParticipationRepository participationRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    EventRepository eventRepository;

    @Override
    public Participation getParticipationById(long id) {
        return participationRepository.findById(id).orElse(null);
    }

    @Override
    public List<Participation> getAllPaticipation() {
        return (List<Participation>)participationRepository.findAll();
    }

    @Override
    public void Participate(Participation p, long userId, long evId) {
        User user = userRepository.findById(userId).orElse(null);
        Event event = eventRepository.findById(evId).orElse(null);
        if (user!=null && event!=null && event.getNumber()<=event.getMaxnumber()) {
            p.setUser(user);
            p.setEv(event);
            p.setParticipDate(new Date());
           event.setNumber(event.getNumber()+1);
            participationRepository.save(p);
           eventRepository.save(event);
        }
    }

    @Override
    public void DeleteParticipation(long id) {
        participationRepository.deleteById(id);
    }
}
