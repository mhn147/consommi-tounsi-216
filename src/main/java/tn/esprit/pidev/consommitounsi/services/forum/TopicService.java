package tn.esprit.pidev.consommitounsi.services.forum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pidev.consommitounsi.entities.user.User;
import tn.esprit.pidev.consommitounsi.entities.forum.Topic;
import tn.esprit.pidev.consommitounsi.entities.forum.Star;
import tn.esprit.pidev.consommitounsi.repositories.user.UserRepository;
import tn.esprit.pidev.consommitounsi.repositories.forum.TopicRepository;
import tn.esprit.pidev.consommitounsi.repositories.forum.StarRepository;

import java.util.*;
import java.util.stream.Collectors;

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

    public List<Topic> getAllOrderedByPopularity() {
        List <Topic> topics=(List <Topic>)topicRepository.findAll();
        HashMap<Topic, Double> starScores=new HashMap<>();
        HashMap<Topic, Double> postScores=new HashMap<>();
        HashMap<Topic, Double> postLengthScores=new HashMap<>();
        double maxStarScores=1, maxPostScores=1, maxPostLengthScores=1;
        for (Topic t : topics) {
            double score=t.getStars().stream().mapToInt(Star::getValue).sum();
            if (score>maxStarScores)
                maxStarScores=score;
            starScores.put(t, score);
            score = t.getPosts().size();
            if (score>maxPostScores)
                maxPostScores=score;
            postScores.put(t, score);
            score=t.getPosts().stream().mapToInt(p->p.getContent().length()).average().orElse(0);
            if (score>maxPostLengthScores)
                maxPostLengthScores=score;
            postLengthScores.put(t, score);
        }
        HashMap<Topic, Double> result=new HashMap<>();
        for (Topic t : topics) {
            double sum=(starScores.get(t)/maxStarScores)*100*3;
            sum+=(postScores.get(t)/maxPostScores)*100*2;
            sum+=(postLengthScores.get(t)/maxPostLengthScores)*100;
            result.put(t, sum/6);
        }
        ArrayList<Map.Entry<Topic, Double>> listResult=new ArrayList<>(result.entrySet());
        Collections.sort(listResult, (e1, e2)->e2.getValue().compareTo(e1.getValue()));
        return listResult.stream().map(Map.Entry::getKey).collect(Collectors.toList());
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
