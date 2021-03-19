package tn.esprit.pidev.consommitounsi.services.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pidev.consommitounsi.entities.products.Product;
import tn.esprit.pidev.consommitounsi.entities.products.Rating;
import tn.esprit.pidev.consommitounsi.repositories.products.ProductRepository;
import tn.esprit.pidev.consommitounsi.repositories.products.RatingRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;


    //getting all books record by using the method findaAll() of CrudRepository
    public List<Product> getAllProducts(String keyword, Double min, Double max) {

        if (keyword != null && min != null && max != null) {
            return productRepository.searchByKeywordAndPrice(min, max, keyword);
        } else if (keyword == null && min != null && max != null) {
            return productRepository.searchByPriceRange(min, max);

        } else if (keyword != null) {

            return productRepository.search(keyword);
        }


        List<Product> products = new ArrayList<Product>();
        productRepository.findAll().forEach(product1 -> products.add(product1));
        return products;

    }

    //getting a specific record by using the method findById() of CrudRepository
    public Product getProductById(Long id) {
        return productRepository.findById(id).get();
    }

    //saving a specific record by using the method save() of CrudRepository
    public void saveOrUpdate(Product product) {
        productRepository.save(product);
    }


    //deleting a specific record by using the method deleteById() of CrudRepository

    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    //updating a record
    public void update(Product product, Long id) {
        productRepository.save(product);
    }



    //public void rate(long productId, long userId, int value) {
      //  Rating rating = RatingRepository.getRating(ratingId, userId);
        //if (rating!=null) {
          //  rating.setValue(value);
            //ratingRepository.save(rating);
        //}

        //}
    }





