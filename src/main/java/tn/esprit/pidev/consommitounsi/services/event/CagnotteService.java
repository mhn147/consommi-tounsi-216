package tn.esprit.pidev.consommitounsi.services.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pidev.consommitounsi.entities.events.Cagnotte;
import tn.esprit.pidev.consommitounsi.entities.events.Event;
import tn.esprit.pidev.consommitounsi.entities.events.Ticket;
import tn.esprit.pidev.consommitounsi.entities.user.User;
import tn.esprit.pidev.consommitounsi.repositories.event.CagnotteRepository;
import tn.esprit.pidev.consommitounsi.repositories.event.DonationRepository;
import tn.esprit.pidev.consommitounsi.repositories.event.EventRepository;
import tn.esprit.pidev.consommitounsi.repositories.event.TicketRepository;
import tn.esprit.pidev.consommitounsi.repositories.user.UserRepository;

@Service
public class CagnotteService implements ICagnotteService{

    @Autowired
    TicketRepository ticketRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CagnotteRepository cagnotteRepository;

    @Override
    public void bet(Ticket t, long userId, long cagnotteId) {
        User user = userRepository.findById(userId).orElse(null);
        Cagnotte cagnotte = cagnotteRepository.findById(cagnotteId).orElse(null);
        if (user!=null && cagnotte!=null) {
            t.setUser(user);
            t.setCagnotte(cagnotte);

            ticketRepository.save(t);
        }
    }
    @Override
    public void saveOrUpdateCagnotte(Cagnotte cagnotte) {
        cagnotteRepository.save(cagnotte);
    }
}
