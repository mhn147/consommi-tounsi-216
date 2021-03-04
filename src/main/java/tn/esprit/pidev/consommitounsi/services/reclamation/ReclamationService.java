package tn.esprit.pidev.consommitounsi.services.reclamation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pidev.consommitounsi.entities.User;
import tn.esprit.pidev.consommitounsi.entities.reclamation.Reclamation;
import tn.esprit.pidev.consommitounsi.entities.reclamation.ReclamationDecision;
import tn.esprit.pidev.consommitounsi.repositories.reclamation.ReclamationRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ReclamationService implements IReclamationService{
    @Autowired
    ReclamationRepository reclamationRepository;

    public void addOrUpdate(Reclamation rec) {
        reclamationRepository.save(rec);
    }

    public void updateDecision(long id, ReclamationDecision decision) {
        Optional<Reclamation> oRec = reclamationRepository.findById(id);
        if (oRec.isPresent()) {
            Reclamation rec = oRec.get();
            rec.setDecision(decision);
            reclamationRepository.save(rec);
        }
    }

    public Reclamation getById(long id) {
        Optional<Reclamation> reclamation = reclamationRepository.findById(id);
        return reclamation.orElse(null);
    }

        public List<Reclamation> getAll() {
            return (List<Reclamation>) reclamationRepository.findAll();
        }

        public void delete(long id) {
            reclamationRepository.deleteById(id);
        }

}
