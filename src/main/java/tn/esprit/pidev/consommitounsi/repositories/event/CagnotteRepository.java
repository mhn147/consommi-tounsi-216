package tn.esprit.pidev.consommitounsi.repositories.event;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import tn.esprit.pidev.consommitounsi.entities.events.Cagnotte;
import tn.esprit.pidev.consommitounsi.entities.events.Event;

import java.util.List;

public interface CagnotteRepository extends CrudRepository<Cagnotte, Long> {

}
