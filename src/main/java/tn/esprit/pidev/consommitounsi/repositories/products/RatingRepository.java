package tn.esprit.pidev.consommitounsi.repositories.products;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import tn.esprit.pidev.consommitounsi.entities.products.Product;
import tn.esprit.pidev.consommitounsi.entities.products.Rating;

public interface RatingRepository extends CrudRepository<Rating,Long> {
    @Query("select r from Rating r where r.product.id = :productId and r.user.id = :userId")
    Rating getRating(@Param("productId") long productId, @Param("userId")long userId);

}
