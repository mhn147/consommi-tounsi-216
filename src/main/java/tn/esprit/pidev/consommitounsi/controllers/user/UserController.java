package tn.esprit.pidev.consommitounsi.controllers.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidev.consommitounsi.entities.user.User;
import tn.esprit.pidev.consommitounsi.entities.user.UserType;
import tn.esprit.pidev.consommitounsi.services.user.IUserService;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    IUserService userService;

    @PostMapping("/user/add")
    @ResponseBody
    public int addUser(@RequestBody User user) {
        int error=0;
        if (userService.getByUsernameOrEmail(user.getUsername())!=null)
            error=1;
        else if (userService.getByUsernameOrEmail(user.getEmail())!=null)
            error=2;
        else
        {
            //hash password
            user.setType(UserType.CUSTOMER);
            userService.addOrUpdate(user);
        }
        return error;
    }

    @PostMapping("/user/update")
    @ResponseBody
    public void updateUser(@RequestBody User user) {
        //hash password
        user.setType(userService.getById(user.getId()).getType());
        userService.addOrUpdate(user);
    }

    @PutMapping("/user/type/{id}/{type}")
    @ResponseBody
    public void updateUserType(@PathVariable("id")long id, @PathVariable("type")UserType type) {
        userService.updateType(id, type);
    }

    @GetMapping("/user/get/{id}")
    @ResponseBody
    public User getUserById(@PathVariable("id")long id) {
        return userService.getById(id);
    }

    @GetMapping("/user/find/{username}")
    @ResponseBody
    public User getUserByUsernameOrEmail(@PathVariable("username")String username) {
        return userService.getByUsernameOrEmail(username);
    }

    @GetMapping("/user/getAll")
    @ResponseBody
    public List<User> getAllUsers() {
        return userService.getAll();
    }

    @DeleteMapping("/user/delete/{id}")
    @ResponseBody
    public void deleteUser(@PathVariable("id")long id) {
        userService.delete(id);
    }
}
