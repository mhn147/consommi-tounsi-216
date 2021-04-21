package tn.esprit.pidev.consommitounsi.controllers.event;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidev.consommitounsi.entities.events.Donation;
import tn.esprit.pidev.consommitounsi.entities.events.Participation;
import tn.esprit.pidev.consommitounsi.services.event.ParticipationService;

import java.util.List;

@RestController
@EnableAutoConfiguration
public class ParticipationController {

    @Autowired
    ParticipationService participationService;


    @PostMapping("/Participation/{userId}/{evId}")
    @ResponseBody
    public void addParticipation(@RequestBody Participation p, @PathVariable("userId") long userId, @PathVariable("evId") long evId) {
        participationService.Participate(p,userId,evId);

    }
    @GetMapping("/Participations")
    @ResponseBody
    public List<Participation> getAllParticipation() {
        return participationService.getAllPaticipation();
    }

    @GetMapping("/Participation/{id}")
    @ResponseBody
    public Participation getParticipationById(@PathVariable("id") long id) {
        return participationService.getParticipationById(id);
    }

    @DeleteMapping("/deleteParticipation/{id}")
    @ResponseBody
    public void deleteDonation(@PathVariable("id")long id) {
        participationService.DeleteParticipation(id);
    }



}
