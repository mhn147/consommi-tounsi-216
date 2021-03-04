package tn.esprit.pidev.consommitounsi.controllers.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidev.consommitounsi.entities.advertisements.Advertisement;
import tn.esprit.pidev.consommitounsi.services.products.AdvertisementService;

import java.util.List;

@RestController
@RequestMapping
public class AdvertisementController {

    @Autowired
    AdvertisementService advertisementService;

    @GetMapping("/advertisements")
    private List<Advertisement> getAllAdvertisements()
    {
        return advertisementService.getAllAdvertisement();

    }

    @GetMapping("/advertisements/{id}")
    private Advertisement getAdvertisement(@PathVariable("advertisementid") Long id)
    {
        return advertisementService.getAdvertisementById(id);
    }

    @DeleteMapping("/advertisements/{id}")
    private void deleteAdvertisement(@PathVariable("id") Long id )
    {
        advertisementService.delete(id);
    }

    //creating post mapping that post the ad detail in the database
    @PostMapping("/advertisements")
    private long saveAdvertisement(@RequestBody Advertisement advertisement)
    {
        advertisementService.saveOrUpdate(advertisement);
        return  advertisement.getId();
    }

    //creating put mapping that updates the ad detail
    @PutMapping("/advertisements/{id}")
    private Advertisement update(@RequestBody Advertisement advertisement)
    {
        advertisementService.saveOrUpdate(advertisement);
        return advertisement;
    }
}
