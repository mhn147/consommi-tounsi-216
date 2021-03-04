package tn.esprit.pidev.consommitounsi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pidev.consommitounsi.entities.User;
import tn.esprit.pidev.consommitounsi.entities.UserType;
import tn.esprit.pidev.consommitounsi.entities.common.Address;
import tn.esprit.pidev.consommitounsi.repositories.AddressRepository;
import tn.esprit.pidev.consommitounsi.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    AddressRepository addressRepository;

    public void addOrUpdate(User user) {
        userRepository.save(user);
    }

    public void updateType(long id, UserType type) {
        Optional<User> oUser = userRepository.findById(id);
        if (oUser.isPresent()) {
            User user = oUser.get();
            user.setType(type);
            userRepository.save(user);
        }
    }

    public User getById(long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User getByUsernameOrEmail(String username) {
        return userRepository.getUserByUsernameOrEmail(username);
    }

    public List<User> getAll() {
        return (List<User>) userRepository.findAll();
    }

    public void delete(long id) {
        userRepository.deleteById(id);
    }

    public void addAddress(Address a, long userId) {
        User u = userRepository.findById(userId).orElse(null);
        if (u!=null) {
            a.setUser(u);
            addressRepository.save(a);
        }
    }

    public void updateAddress(Address a) {
        Address address = addressRepository.findById(a.getId()).orElse(null);
        if (address!=null) {
            a.setUser(address.getUser());
            addressRepository.save(a);
        }
    }

    public void deleteAddressById(long id) {
        addressRepository.deleteById(id);
    }

    public Address getAddressById(long id) {
        return addressRepository.findById(id).orElse(null);
    }

    public List<Address> getUserAddresses(long userId) {
        return addressRepository.getUserAddresses(userId);
    }
}
