package tn.esprit.pidev.consommitounsi.services.user;

import tn.esprit.pidev.consommitounsi.entities.user.User;
import tn.esprit.pidev.consommitounsi.entities.user.UserType;
import tn.esprit.pidev.consommitounsi.entities.common.Address;

import java.util.List;

public interface IUserService {
    void addOrUpdate(User user);
    void updateType(long id, UserType type);
    User getById(long id);
    User getByUsernameOrEmail(String username);
    List<User> getAll();
    void delete(long id);
    void addAddress(Address a, long userId);
    void updateAddress(Address a);
    void deleteAddressById(long id);
    Address getAddressById(long id);
    List<Address> getUserAddresses(long userId);
}
