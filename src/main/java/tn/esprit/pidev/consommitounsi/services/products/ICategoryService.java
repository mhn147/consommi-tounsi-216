package tn.esprit.pidev.consommitounsi.services.products;

import tn.esprit.pidev.consommitounsi.entities.products.Category;

import java.util.List;

public interface ICategoryService {
    List<Category> getAllAdvertisement();
    Category getCategoryById(Long id);
    List<Category> getAll();
    Category saveOrUpdate(Category category);
    void delete( Long id);
    void update(Category category, Long id);
}
