package tn.esprit.pidev.consommitounsi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pidev.consommitounsi.entities.User;
import tn.esprit.pidev.consommitounsi.entities.UserType;
import tn.esprit.pidev.consommitounsi.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {
    @Autowired
    UserRepository userRepository;

    public void addOrUpdate(User user) {
        userRepository.save(user);
    }

    public void updateType(long id, UserType type) {
        Optional<User> oUser=userRepository.findById(id);
        if (oUser.isPresent()) {
            User user = oUser.get();
            user.setType(type);
            userRepository.save(user);
        }
    }

    public User getById(long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
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
}
