package tn.esprit.pidev.consommitounsi.services.products;

import tn.esprit.pidev.consommitounsi.entities.advertisements.Advertisement;

import java.util.List;

public interface IAdvertisementService {
    List<Advertisement> getAllAdvertisement();
    Advertisement getAdvertisementById(Long id);
    void saveOrUpdate(Advertisement advertisement);
    void delete( Long id);
    void update(Advertisement advertisement, Long id);
}
