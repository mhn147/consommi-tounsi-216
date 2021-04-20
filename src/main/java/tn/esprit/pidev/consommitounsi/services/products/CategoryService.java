package tn.esprit.pidev.consommitounsi.services.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pidev.consommitounsi.entities.products.Category;
import tn.esprit.pidev.consommitounsi.repositories.products.CategoryRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService implements ICategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    //getting all categories record by using the method findaAll() of CrudRepository


    public List<Category> getAll()
    {
        List<Category> categories = new ArrayList<Category>();
        categoryRepository.findAll().forEach(c-> categories.add(c));
        return categories;
    }

    public List<Category> getAllAdvertisement()
    {
        List<Category> category = new ArrayList<Category>();
        categoryRepository.findAll().forEach(category1-> category.add(category1));
        return category;

    }

    //getting a specific record by using the method findById() of CrudRepository
    public Category getCategoryById(Long id)
    {
        return categoryRepository.findById(id).get();
    }

    //saving a specific record by using the method save() of CrudRepository
    public Category saveOrUpdate(Category category)
    {
        return categoryRepository.save(category);
    }


    //deleting a specific record by using the method deleteById() of CrudRepository

    public void delete( Long id)
    {
        categoryRepository.deleteById(id);
    }

    //updating a record
    public void update(Category category, Long id)
    {
        categoryRepository.save(category);
    }
}
