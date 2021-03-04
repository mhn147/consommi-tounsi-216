package tn.esprit.pidev.consommitounsi.services.delivery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pidev.consommitounsi.entities.user.User;
import tn.esprit.pidev.consommitounsi.repositories.delivery.DeliveryRepository;
import tn.esprit.pidev.consommitounsi.services.user.UserService;

@Service
public class DeliveryService extends UserService implements IDeliveryService {

    @Autowired
    DeliveryRepository deliveryRepository;

    public User getByUserType(String type) {
        return deliveryRepository.getUserByUserType(type);
    }

    public User getByUsernameOrEmail(String username) {
        return deliveryRepository.getUserByUsernameOrEmail(username);
    }

}
