package tn.esprit.pidev.consommitounsi.services.event;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pidev.consommitounsi.entities.events.Cagnotte;

import tn.esprit.pidev.consommitounsi.entities.events.Event;
import tn.esprit.pidev.consommitounsi.entities.events.Ticket;
import tn.esprit.pidev.consommitounsi.entities.user.User;
import tn.esprit.pidev.consommitounsi.entities.user.UserErrors;
import tn.esprit.pidev.consommitounsi.repositories.event.CagnotteRepository;

import tn.esprit.pidev.consommitounsi.repositories.event.TicketRepository;
import tn.esprit.pidev.consommitounsi.repositories.user.UserRepository;

import java.util.Date;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;

@Service
public class CagnotteService implements ICagnotteService{

    @Autowired
    TicketRepository ticketRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CagnotteRepository cagnotteRepository;

    public static final String ACCOUNT_SID = "ACfd2452c214604ff33b015cf75bff9348";
    public static final String AUTH_TOKEN = "ce2fceb2a81624de8d1d97e443f878ee";

    @Override
    public List<Cagnotte> getAllCagnotte() {
        return (List<Cagnotte>)cagnotteRepository.findAll();
    }
    @Override
    public List<Ticket> getAllticket(){return (List<Ticket>)ticketRepository.findAll(); }

    @Override
    public void bet(Ticket t, long userId, long cagnotteId) {
        User user = userRepository.findById(userId).orElse(null);
        Cagnotte cagnotte = cagnotteRepository.findById(cagnotteId).orElse(null);
        int I = cagnotte.getJackpot();
        if (user!=null && cagnotte!=null && I<2000 ) {
            t.setUser(user);
            t.setCagnotte(cagnotte);
            cagnotte.setJackpot(cagnotte.getJackpot()+10);
            t.setTicketDate(new Date());
            ticketRepository.save(t);
        }

    }
    @Override
    public void saveOrUpdateCagnotte(Cagnotte cagnotte) {
        cagnotteRepository.save(cagnotte);
    }

    @Override
    public Ticket getWinner() {


        long sum = ticketRepository.count();
        long leftLimit = 1L;
        long rightLimit = sum + 1L;

    long generatedLong = leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
    Ticket winner = ticketRepository.findById(generatedLong).orElse(null);

        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(new PhoneNumber(winner.getUser().getPhoneNumber()),
                new PhoneNumber("+19382385454"),
                "Hi "+winner.getUser().getPhoneNumber() +

                        "Congratulations, you’ve won the $500  Gift  Grand Prize in our ‘$500 Summer Giveaway’ ").create();

        System.out.println(message.getSid());
        return winner;
    }
}

