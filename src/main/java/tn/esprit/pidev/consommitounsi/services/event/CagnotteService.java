package tn.esprit.pidev.consommitounsi.services.event;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.pidev.consommitounsi.entities.events.Cagnotte;

import tn.esprit.pidev.consommitounsi.entities.events.Ticket;
import tn.esprit.pidev.consommitounsi.entities.user.User;
import tn.esprit.pidev.consommitounsi.entities.user.UserType;
import tn.esprit.pidev.consommitounsi.repositories.event.CagnotteRepository;

import tn.esprit.pidev.consommitounsi.repositories.event.TicketRepository;
import tn.esprit.pidev.consommitounsi.repositories.user.UserRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class CagnotteService implements ICagnotteService {

    @Autowired
    TicketRepository ticketRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CagnotteRepository cagnotteRepository;

    public static final String ACCOUNT_SID = "ACaca2408ccb8f8ace1456705e087746de";
    public static final String AUTH_TOKEN = "127a70d8a3a0d011e4d48b7ce129d0c3";

    @Override
    public List<Cagnotte> getAllCagnotte() {
        return (List<Cagnotte>) cagnotteRepository.findAll();
    }

    @Override
    public List<Ticket> getAllticket() {
        return (List<Ticket>) ticketRepository.findAll();
    }

    @Override
    public List<Ticket> getMyticket(long userId) {
        return (List<Ticket>) ticketRepository.getTicketById(userId);
    }

    @Override
    public void bet(Ticket t, long userId, long cagnotteId) throws ParseException {
        User user = userRepository.findById(userId).orElse(null);
        Cagnotte cagnotte = cagnotteRepository.findById(cagnotteId).orElse(null);
        int I = cagnotte.getJackpot();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date majeur = sdf.parse("2001-01-01 00:00:00");
        if (user != null && cagnotte != null && I < 2000 && user.getBirthDate().before(majeur) && limit(userId) && !cagnotte.isExpired()) {
            t.setUser(user);
            t.setCagnotte(cagnotte);
            cagnotte.setJackpot(cagnotte.getJackpot() + 10);
            t.setTicketDate(new Date());
            t.setValide(false);
            ticketRepository.save(t);
        }

    }

    @Override
    public void SetLimit(long userId, int limit) {
        User user = userRepository.findById(userId).orElse(null);
        assert user != null;
        user.setBettingLimit(limit);
        userRepository.save(user);
    }

    @Override
    public void DeleteTicket(long id) {
        ticketRepository.deleteById(id);

    }

    @Override
    public void DeleteCagnotte(long id) {
        cagnotteRepository.deleteById(id);
    }

    @Override
    public boolean limit(long userId) {
        User user = userRepository.findById(userId).orElse(null);
        List<Ticket> ticket = (List<Ticket>) ticketRepository.getTicketById(userId);
        int j = 0;
        for (int i = 0; i < ticket.size(); i++) {
            j++;
        }
        if (j == user.getBettingLimit()) {
            return false;
        } else return true;
    }

    @Override
    public void saveOrUpdateCagnotte(Cagnotte cagnotte) {
        Date dt = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, 1);
        dt = c.getTime();
        cagnotte.setJackpot(0);
        cagnotte.setCagnotteDate(dt);

        cagnotteRepository.save(cagnotte);
    }

    @Override
    public Ticket getWinner(long cagnotteId) {

        List<Ticket> tik = (List<Ticket>) ticketRepository.getTicketByCagnotte(cagnotteId);
        Cagnotte cagnotte = cagnotteRepository.findById(cagnotteId).orElse(null);
        Ticket winner = tik.get(new Random().nextInt(tik.size()));
        winner.setValide(true);
        assert cagnotte != null;
        cagnotte.setExpired(true);
        ticketRepository.save(winner);


         /*   Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
            Message message = Message.creator(new PhoneNumber(winner.getUser().getPhoneNumber()),
                    new PhoneNumber("+13139864132"),
                    "Hi " + winner.getUser().getPhoneNumber() +

                            "Congratulations, you’ve won the $500  Gift  Grand Prize in our ‘$500 Summer Giveaway’ ").create();

            System.out.println(message.getSid());*/
        return winner;

    }

    @Scheduled(fixedRate = 10000L)
    @Override
    public void RefreshCagnotte() {
        cagnotteRepository.RefreshCagnotte();

    }
}

