package tn.esprit.pidev.consommitounsi.controllers.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidev.consommitounsi.entities.events.Cagnotte;
import tn.esprit.pidev.consommitounsi.entities.events.Donation;
import tn.esprit.pidev.consommitounsi.entities.events.Event;
import tn.esprit.pidev.consommitounsi.entities.events.Ticket;
import tn.esprit.pidev.consommitounsi.repositories.event.CagnotteRepository;
import tn.esprit.pidev.consommitounsi.repositories.event.TicketRepository;
import tn.esprit.pidev.consommitounsi.services.event.CagnotteService;

@RestController
@EnableAutoConfiguration
public class CagnotteController {

    @Autowired
    CagnotteService cagnotteService;


    @PostMapping("/bet/{userId}/{evId}")
    @ResponseBody
    public void bet(@RequestBody Ticket t, @PathVariable("userId") long userId, @PathVariable("cagnotteId") long cagnotteId) {
        cagnotteService.bet(t,userId,cagnotteId);

    }


    @PostMapping("/AddCagnotte")
    public Cagnotte addCagnotte(@RequestBody Cagnotte cagnotte) {
        cagnotteService.saveOrUpdateCagnotte(cagnotte);
        return cagnotte;
    }

}
