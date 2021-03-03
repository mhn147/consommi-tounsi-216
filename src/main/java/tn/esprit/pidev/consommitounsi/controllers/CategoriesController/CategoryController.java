package tn.esprit.pidev.consommitounsi.controllers.CategoriesController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidev.consommitounsi.entities.products.Category;
import tn.esprit.pidev.consommitounsi.services.products.CategoryService;

import java.util.List;

@RestController
@RequestMapping
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping("/categories")
    private List<Category> getAllCategories()
    {
        return categoryService.getAllAdvertisement();

    }

    @GetMapping("/categories/{id}")
    private Category getCategory(@PathVariable("categoryid") Long id)
    {
        return categoryService.getCategoryById(id);
    }

    @DeleteMapping("/categories/{id}")
    private void deleteCategory(@PathVariable("id") Long id )
    {
        categoryService.delete(id);
    }

    //creating post mapping that post the ad detail in the database
    @PostMapping("/categories")
    private long saveCategory(@RequestBody Category category)
    {
        categoryService.saveOrUpdate(category);
        return  category.getId();
    }
    //creating put mapping that updates the ad detail
    @PutMapping("/categories/{id}")
    private Category update(@RequestBody Category category)
    {
        categoryService.saveOrUpdate(category);
        return category;
    }


}
