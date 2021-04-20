package tn.esprit.pidev.consommitounsi.controllers.category;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import tn.esprit.pidev.consommitounsi.entities.products.Category;
import tn.esprit.pidev.consommitounsi.entities.products.Product;
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
    private Category getCategory(@PathVariable("id") Long id)
    {
        return categoryService.getCategoryById(id);
    }

    @DeleteMapping("/categories/{id}")
    private void deleteCategory(@PathVariable("id") Long id )
    {
        categoryService.delete(id);
    }


    @PostMapping("/categories")
    private long saveCategory(@RequestBody Category category)
    {
        categoryService.saveOrUpdate(category);
        return  category.getId();
    }

    @PostMapping("/categories/{categoryId}/products")
    private Category assignProductToCategory(@PathVariable("categoryId") Long categoryId, @RequestBody Product product)
    {
        return categoryService.assignProductToCategory(categoryId, product);
    }

    //creating put mapping that updates the ad detail
    @PutMapping("/categories/{id}")
    private Category update(@RequestBody Category category)
    {
        categoryService.saveOrUpdate(category);
        return category;
    }



}
