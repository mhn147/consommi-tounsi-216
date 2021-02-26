package tn.esprit.pidev.consommitounsi.services.user;

import tn.esprit.pidev.consommitounsi.entities.user.User;
import tn.esprit.pidev.consommitounsi.entities.user.UserType;

import java.util.List;

public interface IUserService {
    void addOrUpdate(User user);
    void updateType(long id, UserType type);
    User getById(long id);
    User getByUsernameOrEmail(String username);
    List<User> getAll();
    void delete(long id);
}
