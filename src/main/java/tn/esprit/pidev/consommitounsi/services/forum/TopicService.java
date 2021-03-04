package tn.esprit.pidev.consommitounsi.services.forum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pidev.consommitounsi.entities.user.User;
import tn.esprit.pidev.consommitounsi.entities.forum.Topic;
import tn.esprit.pidev.consommitounsi.entities.forum.Star;
import tn.esprit.pidev.consommitounsi.repositories.user.UserRepository;
import tn.esprit.pidev.consommitounsi.repositories.forum.TopicRepository;
import tn.esprit.pidev.consommitounsi.repositories.forum.StarRepository;

import java.util.Date;
import java.util.List;

@Service
public class TopicService implements ITopicService {
    @Autowired
    TopicRepository topicRepository;
    @Autowired
    StarRepository starRepository;
    @Autowired
    UserRepository userRepository;

    public void add(Topic t, long userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user!=null) {
            t.setDate(new Date());
            t.setUser(user);
            topicRepository.save(t);
        }
    }

    public void update(Topic t) {
        Topic topic = topicRepository.findById(t.getId()).orElse(null);
        if (topic!=null) {
            topic.setTitle(t.getTitle());
            topic.setDescription(t.getDescription());
            topicRepository.save(topic);
        }
    }

    public Topic getById(long id) {
        return topicRepository.findById(id).orElse(null);
    }

    public List<Topic> getAllOrderedByDate() {
        return topicRepository.findAllOrderedByDate();
    }

    public void delete(long id) {
        topicRepository.deleteById(id);
    }

    public void rate(long topicId, long userId, int value) {
        Star star=starRepository.getStar(topicId, userId);
        if (star!=null) {
            star.setValue(value);
            starRepository.save(star);
        }
        else {
            Topic topic = topicRepository.findById(topicId).orElse(null);
            User user = userRepository.findById(userId).orElse(null);
            if (topic!=null && user!=null) {
                star = new Star();
                star.setValue(value);
                star.setTopic(topic);
                star.setUser(user);
                starRepository.save(star);
            }
        }
    }
}
