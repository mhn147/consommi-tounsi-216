package tn.esprit.pidev.consommitounsi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidev.consommitounsi.entities.user.User;
import tn.esprit.pidev.consommitounsi.entities.user.UserErrors;
import tn.esprit.pidev.consommitounsi.entities.user.UserType;
import tn.esprit.pidev.consommitounsi.entities.common.Address;
import tn.esprit.pidev.consommitounsi.services.user.IUserService;
import tn.esprit.pidev.consommitounsi.utils.UserSecurity;
import tn.esprit.pidev.consommitounsi.utils.UserSession;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    IUserService userService;

    @PostMapping("/users/login")
    @ResponseBody
    public User login() {
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        if (auth instanceof AnonymousAuthenticationToken || auth == null)
            return null;
        User user=userService.getByUsernameOrEmail(auth.getName());
        UserSession.setUser(user);
        return user;
    }

    @PostMapping("/users")
    @ResponseBody
    public UserErrors addUser(@RequestBody User user) {
        if (user.getUsername().isEmpty() || user.getEmail().isEmpty() || user.getPassword().isEmpty())
            return UserErrors.ERROR;
        if (userService.getByUsernameOrEmail(user.getUsername())!=null)
            return UserErrors.USERNAME_ALREADY_EXISTS;
        if (userService.getByUsernameOrEmail(user.getEmail())!=null)
            return UserErrors.EMAIL_ALREADY_EXISTS;
        user.setPassword(UserSecurity.encodePassword(user.getPassword()));
        user.setType(UserType.CUSTOMER);
        userService.addOrUpdate(user);
        return UserErrors.SUCCESS;
    }

    @PostMapping("/customer/users/edit")
    @ResponseBody
    public UserErrors updateUser(@RequestBody User user) {
        if (UserSession.hasId(user.getId())||UserSession.isAdmin()) {
            if (user.getUsername().isEmpty() || user.getEmail().isEmpty() || user.getPassword().isEmpty())
                return UserErrors.ERROR;
            User existingUser = userService.getByUsernameOrEmail(user.getUsername());
            if (existingUser != null && existingUser.getId() != user.getId())
                return UserErrors.USERNAME_ALREADY_EXISTS;
            existingUser = userService.getByUsernameOrEmail(user.getEmail());
            if (existingUser != null && existingUser.getId() != user.getId())
                return UserErrors.EMAIL_ALREADY_EXISTS;
            user.setPassword(UserSecurity.encodePassword(user.getPassword()));
            user.setType(userService.getById(user.getId()).getType());
            userService.addOrUpdate(user);
            return UserErrors.SUCCESS;
        }
        return UserErrors.ERROR;
    }

    @PutMapping("/admin/users/{id}/{type}")
    @ResponseBody
    public void updateUserType(@PathVariable("id")long id, @PathVariable("type")UserType type) {
        userService.updateType(id, type);
    }

    @GetMapping("/customer/users/{id}")
    @ResponseBody
    public User getUserById(@PathVariable("id")long id) {
        return userService.getById(id);
    }

    @GetMapping("/customer/users/find/{username}")
    @ResponseBody
    public User getUserByUsernameOrEmail(@PathVariable("username")String username) {
        return userService.getByUsernameOrEmail(username);
    }

    @GetMapping("/customer/users")
    @ResponseBody
    public List<User> getAllUsers() {
        return userService.getAll();
    }

    @DeleteMapping("/admin/users/{id}")
    @ResponseBody
    public void deleteUser(@PathVariable("id")long id) {
        userService.delete(id);
    }

    @PostMapping("/customer/addresses/{userId}")
    @ResponseBody
    public void addAddress(@RequestBody Address a, @PathVariable("userId") long userId) {
        if (UserSession.hasId(userId) || UserSession.isAdmin())
            userService.addAddress(a, userId);
    }

    @PostMapping("/customer/addresses/edit")
    @ResponseBody
    public void updateAddress(@RequestBody Address a) {
        Address address=userService.getAddressById(a.getId());
        if (address!=null&&(UserSession.hasId(address.getUser().getId()) || UserSession.isAdmin()))
            userService.updateAddress(a);
    }

    @GetMapping("/customer/addresses/{id}")
    @ResponseBody
    public Address getAddressById(@PathVariable("id")long id) {
        Address a=userService.getAddressById(id);
        if (a!=null && (UserSession.hasId(a.getUser().getId())||UserSession.isAdmin()||UserSession.isDeliverer()))
            return a;
        return null;
    }

    @GetMapping("/customer/users/{userId}/addresses")
    @ResponseBody
    public List<Address> getUserAddresses(@PathVariable("userId")long userId) {
        if (UserSession.hasId(userId) || UserSession.isAdmin() || UserSession.isDeliverer())
            return userService.getUserAddresses(userId);
        return new ArrayList<>();
    }

    @DeleteMapping("/customer/addresses/{id}")
    @ResponseBody
    public void deleteAddress(@PathVariable("id")long id) {
        Address a=userService.getAddressById(id);
        if (a!=null&&(UserSession.hasId(a.getUser().getId()) || UserSession.isAdmin()))
            userService.deleteAddressById(id);
    }
}
