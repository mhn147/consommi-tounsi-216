package tn.esprit.pidev.consommitounsi.repositories.forum;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.pidev.consommitounsi.entities.forum.Star;

@Repository
public interface StarRepository extends CrudRepository<Star, Long> {
    @Query("select s from Star s where s.topic.id = :topicId and s.user.id = :userId")
    Star getStar(@Param("topicId")long topicId, @Param("userId")long userId);
}
