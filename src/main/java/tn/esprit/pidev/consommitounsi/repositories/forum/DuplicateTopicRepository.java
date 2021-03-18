package tn.esprit.pidev.consommitounsi.repositories.forum;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.pidev.consommitounsi.entities.forum.DuplicateTopic;

@Repository
public interface DuplicateTopicRepository extends CrudRepository<DuplicateTopic, Long> {
    @Query("select dt from DuplicateTopic dt where dt.duplicate.id = :topicId")
    DuplicateTopic findByDuplicate(@Param("topicId") long topicId);
}
