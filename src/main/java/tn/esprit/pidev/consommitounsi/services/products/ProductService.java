package tn.esprit.pidev.consommitounsi.services.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pidev.consommitounsi.entities.forum.Star;
import tn.esprit.pidev.consommitounsi.entities.forum.Topic;
import tn.esprit.pidev.consommitounsi.entities.products.Product;
import tn.esprit.pidev.consommitounsi.entities.products.ProductRating;
import tn.esprit.pidev.consommitounsi.entities.user.User;
import tn.esprit.pidev.consommitounsi.repositories.products.ProductRepository;
import tn.esprit.pidev.consommitounsi.repositories.products.ProductRatingRepository;
import tn.esprit.pidev.consommitounsi.repositories.user.UserRepository;

import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    UserRepository userRepository;

    @Autowired
    RatingService ratingService;
    @Autowired
    ProductRatingRepository productRatingRepository;



    //getting all prod record by using the method findaAll() of CrudRepository
    public List<Product> getAllProducts(String keyword, Double min, Double max) {

        if (keyword != null && min != null && max != null) {
            return productRepository.searchByKeywordAndPrice(min, max, keyword);
        } else if (keyword == null && min != null && max != null) {
            return productRepository.searchByPriceRange(min, max);

        } else if (keyword != null) {

            // increment visits compteur
            // this.ratingService.incrementVisit(products);
            return productRepository.search(keyword);
        }

        // increment visits compteur
        List<Product> products = (List<Product>)productRepository.findAll();
        return products;

    }

    //public List<Product> getAllRatedProducts(Long productId,Long userId) {

      // List<Product> ratedProducts = (List<Product>)this.ratingRepository.getAllRatedProducts(Long productId ,Long userId);

       //return ratedProducts;
    //}

    //getting a specific record by using the method findById() of CrudRepository
    public Product getProductById(Long id) {

        Product product = productRepository.findById(id).orElse(null);
        return product;
    }

    //saving a specific record by using the method save() of CrudRepository
    public void saveOrUpdate(Product product) {
        // Save a rating and add default values
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


   // public ProductRating addRateProduct(long productId, long userId, int starRate) {
     //     Product productToBeRated = this.productRepository.findById(productId).orElse(null);


        //satrine jeyin zeda  zeda makenoush com
       // if (productToBeRated == null)
         //return null;

        //  // if (this.productRatingRepository.userAlreadyRatedTheProduct(productId, userId)) {
        //  //    throw new IllegalArgumentException("User Already rated this product.");
        ////}

//men hne

        // ProductRating productRating = new ProductRating();
        ////productRating=this.productRatingRepository.findById(14L).get();

          //User user = this.userRepository.findById(userId).orElse(null);

        //productRating.setUser(user);
        //productRating.setProduct(productToBeRated);
        //productRating.setGlobalRating(0);
        //productRating.setPersonalRating(0);
       // productRating.incVisitCount();
        // test de validité du starRate
        //productRating.setStarRate(starRate);

        // return this.productRatingRepository.save(productRating);
        //}

        //// ProductRating productRating = new ProductRating();
        //// User user = this.userRepository.findById(userId).orElse(null);
        //// Product product = this.productRepository.findById(productId).get();
        ////long productRatingId = this.productRatingRepository.findIdProductRatingByUserIdAndProductId(user.getId(), product.getId());
        //// productRating = this.productRatingRepository.findById(productRatingId).orElse(null);

// si null donc mafamech rating ml user heka 3al produit heka donc ajout

        //// if (productRating == null) {
        ////productRating.setUser(user);
        ////productRating.setProduct(productToBeRated);
        ////productRating.setGlobalRating(0);
        ////productRating.setPersonalRating(0);
        ////productRating.incVisitCount();
        // test de validité du starRate
        ////productRating.setStarRate(starRate);
        ////return this.productRatingRepository.save(productRating);
//si mch null donc fama rate yekhou l objet w ysetti starRate
        ////} else {
        ////    productRating.setStarRate(starRate);
        ////return this.productRatingRepository.save(productRating);
        ////}
        ////}



        ////public ProductRating addRateProduct ( long productId, long userId, int starRate) {

            ////Product productToBeRated = this.productRepository.findById(productId).orElse(null);

            //satrine jeyin zeda  zeda makenoush com
            //// if (productToBeRated == null)
            ////{
            ////return null;


            ////}

            // if (this.productRatingRepository.userAlreadyRatedTheProduct(productId, userId)) {
            //    throw new IllegalArgumentException("User Already rated this product.");
            //}

//men hne

            //// ProductRating productRating = new ProductRating();

            ////User user = this.userRepository.findById(userId).orElse(null);
            ////productRating.setUser(user);
            ////productRating.setProduct(productToBeRated);
            ////productRating.setGlobalRating(0);
            ////productRating.setPersonalRating(0);
            ////productRating.incVisitCount();
            // test de validité du starRate
            ////productRating.setStarRate(starRate);

            ////return this.productRatingRepository.save(productRating);
            ////}

    public void addRateProduct ( long productId, long userId, int starRate) {

        //  Product productToBeRated = this.productRepository.findById(productId).orElse(null);
        ProductRating productRating = productRatingRepository.getProductRating(productId, userId);
        //ProductRating productRating = new ProductRating();

        // User user = this.userRepository.findById(userId).orElse(null);

        //Product product = this.productRepository.findById(productId).get();

        //long productRatingId = this.productRatingRepository.findIdProductRatingByUserIdAndProductId(user.getId(), product.getId());
        //productRating = this.productRatingRepository.findById(productRatingId).orElse(null);

// si null donc mafamech rating ml user heka 3al produit heka donc ajout
        if (productRating != null) {
            productRating.setStarRate(starRate);
            productRatingRepository.save(productRating);
        } else {
            Product product = productRepository.findById(productId).orElse(null);
            User user = userRepository.findById(userId).orElse(null);
            if (product != null && user != null) {
                productRating = new ProductRating();
                productRating.setStarRate(starRate);
                productRating.setProduct(product);
                productRating.setUser(user);
                productRatingRepository.save(productRating);
            }

        }
    }}
               // productRating.setUser(user);
               // productRating.setProduct(productToBeRated);
               // productRating.setGlobalRating(0);
               // productRating.setPersonalRating(0);
                //productRating.incVisitCount();
                // test de validité du starRate
                //productRating.setStarRate(starRate);
                //return this.productRatingRepository.save(productRating);

//si mch null donc fama rate yekhou l objet w ysetti starRate
           // } else {
             //   productRating.setStarRate(starRate);
               // return this.productRatingRepository.save(productRating);
            //}











