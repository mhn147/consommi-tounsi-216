package tn.esprit.pidev.consommitounsi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import tn.esprit.pidev.consommitounsi.entities.products.Product;
import tn.esprit.pidev.consommitounsi.repositories.ProductRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    //getting all books record by using the method findaAll() of CrudRepository
    public List<Product> getAllProduct()
    {
        List<Product> product = new ArrayList<Product>();
        productRepository.findAll().forEach(product1 -> product.add(product1));
        return product;

}

//getting a specific record by using the method findById() of CrudRepository
public Product getProductById(Long id)
{
    return productRepository.findById(id).get();
}

    //saving a specific record by using the method save() of CrudRepository
    public void saveOrUpdate(Product product)
    {
        productRepository.save(product);
    }



    //deleting a specific record by using the method deleteById() of CrudRepository

    public void delete( Long id)
    {
        productRepository.deleteById(id);
    }

    //updating a record
    public void update(Product product, Long id)
    {
        productRepository.save(product);
    }
}



