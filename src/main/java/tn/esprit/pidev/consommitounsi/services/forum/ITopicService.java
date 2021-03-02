package tn.esprit.pidev.consommitounsi.services.forum;

import tn.esprit.pidev.consommitounsi.entities.forum.Topic;

import java.util.List;

public interface ITopicService {
    void add(Topic t, long userId);
    void update(Topic t);
    Topic getById(long id);
    List<Topic> getAllOrderedByDate();
    void delete(long id);
    void rate(long topicId, long userId, int value);
}
