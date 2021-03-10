package tn.esprit.pidev.consommitounsi.services.event;

import tn.esprit.pidev.consommitounsi.entities.events.Donation;
import tn.esprit.pidev.consommitounsi.entities.events.Event;

import java.util.List;

public interface IDonationService {
    Donation getDonationById(long id);
    List<Donation> getAllDonations();
    void saveOrUpdateDonation(Donation donation);
    void DeleteDonation(long id);

}
