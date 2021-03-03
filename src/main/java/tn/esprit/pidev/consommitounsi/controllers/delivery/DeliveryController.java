package tn.esprit.pidev.consommitounsi.controllers.delivery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidev.consommitounsi.entities.User;
import tn.esprit.pidev.consommitounsi.entities.UserType;
import tn.esprit.pidev.consommitounsi.entities.delivery.Deliverer;
import tn.esprit.pidev.consommitounsi.services.delivery.DeliveryService;
import tn.esprit.pidev.consommitounsi.services.delivery.IDeliveryService;

import java.util.List;

@RestController
public class DeliveryController {
    @Autowired
    IDeliveryService deliveryService;

    @PostMapping("/AddDeliverer")
    @ResponseBody
    public int addDeliverer(@RequestBody User user) {
        int error=0;
        if (deliveryService.getByUsernameOrEmail(user.getUsername())!=null)
            error=1;
        else if (deliveryService.getByUsernameOrEmail(user.getEmail())!=null)
            error=2;
        else
        {
            //hash password
            user.setType(UserType.DELIVERER);
            deliveryService.addOrUpdate(user);
        }
        return error;
    }



    public void updateUserType(@PathVariable("id")long id, @PathVariable("type")UserType type) {
        deliveryService.updateType(id, type);
    }

    @GetMapping("/getDeliverer/{id}")
    @ResponseBody
    public User getDelivererById(@PathVariable("id")long id) {
        return deliveryService.getById(id);
    }

    @GetMapping("/getAllDeliverers")
    @ResponseBody
    public List<User> getAllDeliverers() {
        return deliveryService.getAll();
    }

    @DeleteMapping("/deleteDeliverer/{id}")
    @ResponseBody
    public void deleteDeliverer(@PathVariable("id")long id) {
        deliveryService.delete(id);
    }
}
