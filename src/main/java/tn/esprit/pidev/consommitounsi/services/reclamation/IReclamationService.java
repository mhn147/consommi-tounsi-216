package tn.esprit.pidev.consommitounsi.services.reclamation;

import tn.esprit.pidev.consommitounsi.entities.reclamation.Reclamation;

import java.util.List;

public interface IReclamationService {

    void addOrUpdate(Reclamation rec);
    Reclamation getById(long id);
    List<Reclamation> getAll();
    void delete(long id);
}
