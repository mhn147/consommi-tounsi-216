package tn.esprit.pidev.consommitounsi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pidev.consommitounsi.entities.advertisements.Advertisement;
import tn.esprit.pidev.consommitounsi.entities.products.Product;
import tn.esprit.pidev.consommitounsi.repositories.AdvertisementRepository;
import tn.esprit.pidev.consommitounsi.repositories.ProductRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdvertisementService {
    @Autowired
    AdvertisementRepository advertisementRepository;

    //getting all books record by using the method findaAll() of CrudRepository
    public List<Advertisement> getAllAdvertisement()
    {
        List<Advertisement> advertisement = new ArrayList<Advertisement>();
        advertisementRepository.findAll().forEach(advertisement1-> advertisement.add(advertisement1));
        return advertisement;

    }

    //getting a specific record by using the method findById() of CrudRepository
    public Advertisement getAdvertisementById(Long id)
    {
        return advertisementRepository.findById(id).get();
    }

    //saving a specific record by using the method save() of CrudRepository
    public void saveOrUpdate(Advertisement advertisement)
    {
        advertisementRepository.save(advertisement);
    }



    //deleting a specific record by using the method deleteById() of CrudRepository

    public void delete( Long id)
    {
        advertisementRepository.deleteById(id);
    }

    //updating a record
    public void update(Advertisement advertisement, Long id)
    {
        advertisementRepository.save(advertisement);
    }
}





