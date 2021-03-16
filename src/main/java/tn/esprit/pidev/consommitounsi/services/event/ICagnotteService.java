package tn.esprit.pidev.consommitounsi.services.event;

import tn.esprit.pidev.consommitounsi.entities.events.Cagnotte;
import tn.esprit.pidev.consommitounsi.entities.events.Donation;
import tn.esprit.pidev.consommitounsi.entities.events.Ticket;

public interface ICagnotteService {


    void bet(Ticket ticket, long userId, long cagnotteId);
     void saveOrUpdateCagnotte(Cagnotte cagnotte);
}
