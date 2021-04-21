package tn.esprit.pidev.consommitounsi.repositories.products;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.pidev.consommitounsi.entities.forum.PostLike;
import tn.esprit.pidev.consommitounsi.entities.products.CommentLike;

@Repository
public interface CommentLikeRepository extends CrudRepository<CommentLike, Long> {
    @Query("select cl from CommentLike cl where cl.comment.id = :commentId and cl.user.id = :userId")
    CommentLike findCommentLike(@Param("commentId")long commentId, @Param("userId")long userId);
}
