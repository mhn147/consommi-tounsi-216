package tn.esprit.pidev.consommitounsi.services;

import tn.esprit.pidev.consommitounsi.entities.User;
import tn.esprit.pidev.consommitounsi.entities.UserType;

import java.util.List;

public interface IUserService {
    void addOrUpdate(User user);
    void updateType(long id, UserType type);
    User getById(long id);
    User getByUsernameOrEmail(String username);
    List<User> getAll();
    void delete(long id);
}
