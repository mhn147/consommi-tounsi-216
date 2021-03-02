package tn.esprit.pidev.consommitounsi.repositories.forum;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.pidev.consommitounsi.entities.forum.PostLike;

@Repository
public interface PostLikeRepository extends CrudRepository<PostLike, Long> {
    @Query("select pl from PostLike pl where pl.post.id = :postId and pl.user.id = :userId")
    PostLike findPostLike(@Param("postId")long postId, @Param("userId")long userId);
}
