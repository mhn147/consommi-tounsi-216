package tn.esprit.pidev.consommitounsi.services.delivery;

import tn.esprit.pidev.consommitounsi.entities.User;
import tn.esprit.pidev.consommitounsi.entities.UserType;

import java.util.List;

public interface IDeliveryService {

    void addOrUpdate(User user);
    void updateType(long id, UserType type);
    User getById(long id);
    User getByUserType(String type);
    List<User> getAll();
    void delete(long id);
}
