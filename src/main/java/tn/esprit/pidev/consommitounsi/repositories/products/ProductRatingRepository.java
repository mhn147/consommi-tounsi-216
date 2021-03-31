package tn.esprit.pidev.consommitounsi.repositories.products;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import tn.esprit.pidev.consommitounsi.entities.products.ProductRating;

public interface ProductRatingRepository extends CrudRepository<ProductRating,Long> {
    @Query("select r from ProductRating r where r.product.id = :productId and r.user.id = :userId")
    ProductRating getProductRating (@Param("productId")long productId, @Param("userId")long userId);

   //@Query("select r from ProductRating r where r.product.id = :productId and r.user.id = :userId")
    //ProductRating findIdProductRatingByUserIdAndProductId(@Param("productId") long productId, @Param("userId")long userId);
//
//@Query(nativeQuery = true, value = "SELECT p.id FROM ProductRating p where p.user.id =:userId  and p.product_id = :productId")
//long findIdProductRatingByUserIdAndProductId(long userId ,long productId);

}
