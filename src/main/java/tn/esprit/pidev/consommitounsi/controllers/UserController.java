package tn.esprit.pidev.consommitounsi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidev.consommitounsi.entities.User;
import tn.esprit.pidev.consommitounsi.entities.UserErrors;
import tn.esprit.pidev.consommitounsi.entities.UserType;
import tn.esprit.pidev.consommitounsi.entities.common.Address;
import tn.esprit.pidev.consommitounsi.services.IUserService;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    IUserService userService;

    @PostMapping("/users")
    @ResponseBody
    public UserErrors addUser(@RequestBody User user) {
        if (userService.getByUsernameOrEmail(user.getUsername())!=null)
            return UserErrors.USERNAME_ALREADY_EXISTS;
        if (userService.getByUsernameOrEmail(user.getEmail())!=null)
            return UserErrors.EMAIL_ALREADY_EXISTS;
        //hash password
        user.setType(UserType.CUSTOMER);
        userService.addOrUpdate(user);
        return UserErrors.SUCCESS;
    }

    @PostMapping("/users/edit")
    @ResponseBody
    public UserErrors updateUser(@RequestBody User user) {
        User existingUser=userService.getByUsernameOrEmail(user.getUsername());
        if (existingUser!=null && existingUser.getId()!=user.getId())
            return UserErrors.USERNAME_ALREADY_EXISTS;
        existingUser=userService.getByUsernameOrEmail(user.getEmail());
        if (existingUser!=null && existingUser.getId()!=user.getId())
            return UserErrors.EMAIL_ALREADY_EXISTS;
        //hash password
        user.setType(userService.getById(user.getId()).getType());
        userService.addOrUpdate(user);
        return UserErrors.SUCCESS;
    }

    @PutMapping("/users/{id}/{type}")
    @ResponseBody
    public void updateUserType(@PathVariable("id")long id, @PathVariable("type")UserType type) {
        userService.updateType(id, type);
    }

    @GetMapping("/users/{id}")
    @ResponseBody
    public User getUserById(@PathVariable("id")long id) {
        return userService.getById(id);
    }

    @GetMapping("/users/find/{username}")
    @ResponseBody
    public User getUserByUsernameOrEmail(@PathVariable("username")String username) {
        return userService.getByUsernameOrEmail(username);
    }

    @GetMapping("/users")
    @ResponseBody
    public List<User> getAllUsers() {
        return userService.getAll();
    }

    @DeleteMapping("/users/{id}")
    @ResponseBody
    public void deleteUser(@PathVariable("id")long id) {
        userService.delete(id);
    }

    @PostMapping("/addresses/{userId}")
    @ResponseBody
    public void addAddress(@RequestBody Address a, @PathVariable("userId") long userId) {
        userService.addAddress(a, userId);
    }

    @PostMapping("/addresses/edit")
    @ResponseBody
    public void updateAddress(@RequestBody Address a) {
        userService.updateAddress(a);
    }

    @GetMapping("/addresses/{id}")
    @ResponseBody
    public Address getAddressById(@PathVariable("id")long id) {
        return userService.getAddressById(id);
    }

    @GetMapping("/users/{userId}/addresses")
    @ResponseBody
    public List<Address> getUserAddresses(@PathVariable("userId")long userId) {
        return userService.getUserAddresses(userId);
    }

    @DeleteMapping("/addresses/{id}")
    @ResponseBody
    public void deleteAddress(@PathVariable("id")long id) {
        userService.deleteAddressById(id);
    }
}
