package tn.esprit.pidev.consommitounsi.repositories.reclamation;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import tn.esprit.pidev.consommitounsi.entities.reclamation.Reclamation;

public interface ReclamationRepository extends CrudRepository<Reclamation, Long> {

}
