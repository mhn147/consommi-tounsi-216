package tn.esprit.pidev.consommitounsi.services.event;

import tn.esprit.pidev.consommitounsi.entities.events.Participation;

import java.util.List;

public interface IParticipationService {
    Participation getParticipationById(long id);
    List<Participation> getAllPaticipation();
    void Participate(Participation p,long userId,long evId);
    void DeleteParticipation(long id);
}
