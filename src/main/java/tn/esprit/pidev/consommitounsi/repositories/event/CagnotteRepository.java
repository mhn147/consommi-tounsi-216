package tn.esprit.pidev.consommitounsi.repositories.event;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.scheduling.annotation.Scheduled;
import tn.esprit.pidev.consommitounsi.entities.events.Cagnotte;
import tn.esprit.pidev.consommitounsi.entities.events.Event;

import javax.transaction.Transactional;
import java.util.List;

public interface CagnotteRepository extends CrudRepository<Cagnotte, Long> {
    @Scheduled(fixedRate = 10000L)
    @Transactional
    @Modifying
    @Query("delete from Cagnotte c where c.CagnotteDate <= current_date  ")
    void RefreshCagnotte();
}
