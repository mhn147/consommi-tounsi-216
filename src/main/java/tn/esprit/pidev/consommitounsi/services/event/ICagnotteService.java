package tn.esprit.pidev.consommitounsi.services.event;

import tn.esprit.pidev.consommitounsi.entities.events.Cagnotte;
import tn.esprit.pidev.consommitounsi.entities.events.Ticket;
import tn.esprit.pidev.consommitounsi.entities.user.User;

import java.text.ParseException;
import java.util.List;

public interface ICagnotteService {

     List<Cagnotte> getAllCagnotte();
     List<Ticket> getMyticket(long userId);
    void bet(Ticket ticket, long userId, long cagnotteId) throws ParseException;
     void saveOrUpdateCagnotte(Cagnotte cagnotte);
     Ticket getWinner(long cagnotteId);
    List<Ticket> getAllticket();
    boolean limit(long userID);
     void SetLimit(long userId, int limit);
}
