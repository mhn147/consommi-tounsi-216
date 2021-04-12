package tn.esprit.pidev.consommitounsi.controllers.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidev.consommitounsi.entities.products.Product;
import tn.esprit.pidev.consommitounsi.services.products.ProductService;

import java.util.List;

@RestController
@RequestMapping
public class ProductController {


    @Autowired
    ProductService productService;

    @GetMapping("/products")
    private List<Product> getAllProducts()
    {
        List<Product> products = productService.getAllProduct();
        return products;
    }

    @GetMapping("/products/{id}")
    private Product getProduct(@PathVariable("productid") Long id)
    {
        return productService.getProductById(id);
    }

    @DeleteMapping ("/products/{id}")
    private void deleteProduct(@PathVariable("id") Long id)
    {
        productService.delete(id);
    }

    //creating post mapping that post the book detail in the database
    @PostMapping("/products")
    private long saveProduct(@RequestBody Product product)
    {
        productService.saveOrUpdate(product);
        return  product.getId();
    }

    //creating put mapping that updates the book detail
    @PutMapping("/products")
    private Product update(@RequestBody Product product)
    {
        productService.saveOrUpdate(product);
        return product;
    }
}
