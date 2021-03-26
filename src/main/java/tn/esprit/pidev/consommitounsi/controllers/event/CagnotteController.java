package tn.esprit.pidev.consommitounsi.controllers.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidev.consommitounsi.entities.events.Cagnotte;
import tn.esprit.pidev.consommitounsi.entities.events.Ticket;
import tn.esprit.pidev.consommitounsi.services.event.CagnotteService;

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

    @PostMapping("/bet/{userId}/{cagnotteId}")
    @ResponseBody
    public void bet(@RequestBody Ticket t, @PathVariable("userId") long userId, @PathVariable("cagnotteId") long cagnotteId) {
        cagnotteService.bet(t,userId,cagnotteId);

    }


    @PostMapping("/AddCagnotte")
    public Cagnotte addCagnotte(@RequestBody Cagnotte cagnotte) {
        cagnotteService.saveOrUpdateCagnotte(cagnotte);
        return cagnotte;
    }
    @GetMapping("/winner")
    @ResponseBody
    public Ticket getWinner(){return cagnotteService.getWinner(); }
}
