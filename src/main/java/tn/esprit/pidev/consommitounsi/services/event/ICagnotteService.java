package tn.esprit.pidev.consommitounsi.services.event;

import tn.esprit.pidev.consommitounsi.entities.events.Cagnotte;
import tn.esprit.pidev.consommitounsi.entities.events.Donation;
import tn.esprit.pidev.consommitounsi.entities.events.Ticket;
import tn.esprit.pidev.consommitounsi.entities.user.User;

import java.util.List;

public interface ICagnotteService {

     List<Cagnotte> getAllCagnotte();
    void bet(Ticket ticket, long userId, long cagnotteId);
     void saveOrUpdateCagnotte(Cagnotte cagnotte);
     Ticket getWinner();
    List<Ticket> getAllticket();
}
