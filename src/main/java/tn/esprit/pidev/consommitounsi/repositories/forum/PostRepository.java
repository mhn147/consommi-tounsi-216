package tn.esprit.pidev.consommitounsi.repositories.forum;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.pidev.consommitounsi.entities.forum.Post;

import java.util.List;

@Repository
public interface PostRepository extends CrudRepository<Post, Long> {
    @Query("select p from Post p where p.topic.id = :topicId order by p.date desc")
    List<Post> getAllPostsOrderedByDate(@Param("topicId") long topicId);
}
