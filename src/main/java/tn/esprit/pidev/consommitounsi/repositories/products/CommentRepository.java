package tn.esprit.pidev.consommitounsi.repositories.products;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.pidev.consommitounsi.entities.products.Category;
import tn.esprit.pidev.consommitounsi.entities.products.Comment;

import java.util.List;


@Repository
public interface CommentRepository extends CrudRepository<Comment,Long> {
    @Query("select c from Comment c where c.product.id = :productId order by c.date")
    List<Comment> getAllCommentsOrderedByDate(@Param("productId") long productId);

}
