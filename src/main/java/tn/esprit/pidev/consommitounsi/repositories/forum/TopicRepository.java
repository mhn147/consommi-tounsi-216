package tn.esprit.pidev.consommitounsi.repositories.forum;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.pidev.consommitounsi.entities.forum.Topic;

import java.util.List;

@Repository
public interface TopicRepository extends CrudRepository<Topic, Long> {
    @Query("select t from Topic t order by t.date desc")
    List<Topic> findAllOrderedByDate();
}
