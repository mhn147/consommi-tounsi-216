package tn.esprit.pidev.consommitounsi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.pidev.consommitounsi.entities.products.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product,Long> {


}
