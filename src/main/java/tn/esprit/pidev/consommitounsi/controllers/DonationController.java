package tn.esprit.pidev.consommitounsi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidev.consommitounsi.entities.events.Donation;
import tn.esprit.pidev.consommitounsi.services.event.DonationService;

import java.util.List;

@RestController
@EnableAutoConfiguration
public class DonationController {

    @Autowired
    DonationService donationService;

    @PostMapping("/AddDonation")
    public Donation addDonation(@RequestBody Donation donation) {
        donationService.saveOrUpdateDonation(donation);
        return donation;
    }
    @GetMapping("/Donations")
    @ResponseBody
    public List<Donation> getAllDonations() {
        return donationService.getAllDonations();
    }

    @GetMapping("/Donation/{id}")
    @ResponseBody
    public Donation getDonationById(@PathVariable("id") long id) {
        return donationService.getDonationById(id);
    }

    @DeleteMapping("/deleteDonation/{id}")
    @ResponseBody
    public void deleteDonation(@PathVariable("id")long id) {
        donationService.DeleteDonation(id);
    }

}
