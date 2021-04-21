package tn.esprit.pidev.consommitounsi.repositories.products;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.pidev.consommitounsi.entities.products.Product;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product,Long> {
    @Query("SELECT p FROM Product p WHERE p.name LIKE %?1%"
            + " OR p.description LIKE %?1%"

            + " OR CONCAT(p.price, '') LIKE %?1%")
    public List<Product> search(String keyword);

    @Query("SELECT p from Product p where p.price between :min and :max")
    public  List<Product> searchByPriceRange (@Param("min") double min, @Param ("max")double max);

    @Query("SELECT p FROM Product p WHERE p.price between :min and :max " +
            "AND p.name LIKE %:keyword% OR p.description LIKE %:keyword% OR CONCAT(p.price, '') LIKE %:keyword%")
    public  List<Product> searchByKeywordAndPrice(@Param("min") double min, @Param ("max")double max,
                                                  @Param("keyword")String keyword);





}

