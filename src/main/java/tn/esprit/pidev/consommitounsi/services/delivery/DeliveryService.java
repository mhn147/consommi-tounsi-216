package tn.esprit.pidev.consommitounsi.services.delivery;

import org.springframework.beans.factory.annotation.Autowired;
import tn.esprit.pidev.consommitounsi.entities.User;
import tn.esprit.pidev.consommitounsi.entities.UserType;
import tn.esprit.pidev.consommitounsi.repositories.delivery.DeliveryRepository;
import tn.esprit.pidev.consommitounsi.services.UserService;

import java.util.Optional;

public class DeliveryService extends UserService implements IDeliveryService {

    @Autowired
    DeliveryRepository deliveryRepository;

    public User getByUserType(String type) {
        return deliveryRepository.getUserByUserType(type);
    }

}
