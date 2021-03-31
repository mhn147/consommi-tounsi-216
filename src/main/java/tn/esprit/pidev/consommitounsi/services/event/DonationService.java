package tn.esprit.pidev.consommitounsi.services.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pidev.consommitounsi.entities.events.Donation;
import tn.esprit.pidev.consommitounsi.entities.events.Event;
import tn.esprit.pidev.consommitounsi.entities.user.User;
import tn.esprit.pidev.consommitounsi.repositories.event.DonationRepository;
import tn.esprit.pidev.consommitounsi.repositories.event.EventRepository;
import tn.esprit.pidev.consommitounsi.repositories.user.UserRepository;

import java.util.List;

@Service
public class DonationService implements IDonationService {

    @Autowired
    DonationRepository donationRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    EventRepository eventRepository;

    @Override
    public Donation getDonationById(long id) {
        return donationRepository.findById(id).orElse(null);
    }

    @Override
    public List<Donation> getAllDonations() {
        return (List<Donation>)donationRepository.findAll();
    }

    @Override
    public void Donate(Donation d,long userId,long evId) {
        User user = userRepository.findById(userId).orElse(null);
        Event event = eventRepository.findById(evId).orElse(null);
        if (user!=null && event!=null) {
            d.setUser(user);
            d.setEv(event);

            donationRepository.save(d);
        }

    }

    @Override
    public void DeleteDonation(long id) {
        donationRepository.deleteById(id);
    }
}
