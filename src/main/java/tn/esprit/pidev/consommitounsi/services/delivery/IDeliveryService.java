package tn.esprit.pidev.consommitounsi.services.delivery;

import tn.esprit.pidev.consommitounsi.entities.user.User;
import tn.esprit.pidev.consommitounsi.entities.user.UserType;

import java.util.List;

public interface IDeliveryService {

    void addOrUpdate(User user);
    void updateType(long id, UserType type);
    User getById(long id);
    User getByUsernameOrEmail(String username);
    User getByUserType(String type);
    List<User> getAll();
    void delete(long id);
}
