package tn.esprit.pidev.consommitounsi.services.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pidev.consommitounsi.entities.events.Donation;
import tn.esprit.pidev.consommitounsi.repositories.DonationRepository;

import java.util.List;

@Service
public class DonationService implements IDonationService {

    @Autowired
    DonationRepository donationRepository;

    @Override
    public Donation getDonationById(long id) {
        return donationRepository.findById(id).orElse(null);
    }

    @Override
    public List<Donation> getAllDonations() {
        return (List<Donation>)donationRepository.findAll();
    }

    @Override
    public void saveOrUpdateDonation(Donation donation) {
        donationRepository.save(donation);
    }

    @Override
    public void DeleteDonation(long id) {
        donationRepository.deleteById(id);
    }
}
