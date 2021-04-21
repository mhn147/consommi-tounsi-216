package tn.esprit.pidev.consommitounsi.controllers.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidev.consommitounsi.entities.events.Cagnotte;
import tn.esprit.pidev.consommitounsi.entities.events.Ticket;
import tn.esprit.pidev.consommitounsi.entities.user.UserType;
import tn.esprit.pidev.consommitounsi.repositories.event.TicketRepository;
import tn.esprit.pidev.consommitounsi.services.event.CagnotteService;

import java.text.ParseException;
import java.util.List;

@RestController
@EnableAutoConfiguration
public class CagnotteController {

    @Autowired
    CagnotteService cagnotteService;

    @GetMapping("/Cagnottes")
    @ResponseBody
    public List<Cagnotte> getAllCagnotte() {
        return cagnotteService.getAllCagnotte();
    }
    @GetMapping("/Tickets")
    @ResponseBody
    public List<Ticket> getAlltTickets() {
        return cagnotteService.getAllticket();
    }

    @GetMapping("/Tickets/{userId}")
    @ResponseBody
    public List<Ticket> getMytickets(@PathVariable("userId") long userId) {
        return cagnotteService.getMyticket(userId);
    }

    @PostMapping("/bet/{userId}/{cagnotteId}")
    @ResponseBody
    public void bet(@RequestBody Ticket t, @PathVariable("userId") long userId, @PathVariable("cagnotteId") long cagnotteId) throws ParseException {
        cagnotteService.bet(t,userId,cagnotteId);

    }

    @PutMapping("/ControlBet/{userId}/{limit}")
    @ResponseBody
    public void updateUserLimit(@PathVariable("userId")long userId, @PathVariable("limit") int limit) {
      cagnotteService.SetLimit(userId,limit);
    }

    @PostMapping("/AddCagnotte")
    public Cagnotte addCagnotte(@RequestBody Cagnotte cagnotte) {
        cagnotteService.saveOrUpdateCagnotte(cagnotte);
        return cagnotte;
    }
    @GetMapping("/winner/{cagnotteId}")
    @ResponseBody
    public Ticket getWinner(@PathVariable("cagnotteId") long cagnotteId){return cagnotteService.getWinner(cagnotteId); }
}
