package tn.esprit.pidev.consommitounsi.repositories.reclamation;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.pidev.consommitounsi.entities.reclamation.Reclamation;

@Repository
public interface ReclamationRepository extends CrudRepository<Reclamation, Long> {

}
