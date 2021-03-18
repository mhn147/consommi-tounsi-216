package tn.esprit.pidev.consommitounsi.services.forum;

import net.ricecode.similarity.DiceCoefficientStrategy;
import net.ricecode.similarity.StringSimilarityService;
import net.ricecode.similarity.StringSimilarityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pidev.consommitounsi.entities.forum.DuplicateTopic;
import tn.esprit.pidev.consommitounsi.entities.user.User;
import tn.esprit.pidev.consommitounsi.entities.forum.Topic;
import tn.esprit.pidev.consommitounsi.entities.forum.Star;
import tn.esprit.pidev.consommitounsi.repositories.forum.DuplicateTopicRepository;
import tn.esprit.pidev.consommitounsi.repositories.user.UserRepository;
import tn.esprit.pidev.consommitounsi.repositories.forum.TopicRepository;
import tn.esprit.pidev.consommitounsi.repositories.forum.StarRepository;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class TopicService implements ITopicService {
    @Autowired
    TopicRepository topicRepository;
    @Autowired
    StarRepository starRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    DuplicateTopicRepository duplicateTopicRepository;

    public void add(Topic t, long userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user!=null) {
            t.setDate(new Date());
            t.setUser(user);
            topicRepository.save(t);
            checkDuplicates(t);
        }
    }

    public void update(Topic t) {
        Topic topic = topicRepository.findById(t.getId()).orElse(null);
        if (topic!=null) {
            topic.setTitle(t.getTitle());
            topic.setDescription(t.getDescription());
            topicRepository.save(topic);
            checkDuplicates(topic);
        }
    }

    private void checkDuplicates(Topic t) {
        DuplicateTopic dt=duplicateTopicRepository.findByDuplicate(t.getId());
        if (dt!=null)
            duplicateTopicRepository.delete(dt);
        dt=new DuplicateTopic();
        dt.setDuplicate(t);
        dt.setOriginals(new ArrayList<>());
        StringSimilarityService checkSim = new StringSimilarityServiceImpl(new DiceCoefficientStrategy());
        for (Topic topic : getAllOrderedByDate()) {
            if (topic.getId()!=t.getId() && checkSim.score(t.getTitle(), topic.getTitle())>0.65)
                dt.getOriginals().add(topic);
        }
        if (dt.getOriginals().size()!=0)
            duplicateTopicRepository.save(dt);
    }

    public Topic getById(long id) {
        return topicRepository.findById(id).orElse(null);
    }

    public List<Topic> getAllOrderedByDate() {
        return topicRepository.findAllOrderedByDate();
    }

    public static <T> List<T> orderByPopularity(List<T> elements, int[] coefficients,Function<T, Double>... getScores) {
        List<HashMap<T, Double>> scores=new ArrayList<>();
        for (int i=0;i<getScores.length;i++)
            scores.add(new HashMap<>());
        double[] maxScores=new double[getScores.length];
        Arrays.fill(maxScores, 1);
        for (T element : elements) {
            for (int i=0;i<getScores.length;i++) {
                double score=getScores[i].apply(element);
                if (score>maxScores[i])
                    maxScores[i]=score;
                scores.get(i).put(element, score);
            }
        }
        HashMap<T, Double> finalScore=new HashMap<>();
        for (T element : elements) {
            double sum=0;
            for (int i=0;i<getScores.length;i++)
                sum+=(scores.get(i).get(element)/maxScores[i])*100*coefficients[i];
            finalScore.put(element, sum/Arrays.stream(coefficients).sum());
        }
        ArrayList<Map.Entry<T, Double>> results=new ArrayList<>(finalScore.entrySet());
        Collections.sort(results, (e1, e2)->e2.getValue().compareTo(e1.getValue()));
        return results.stream().map(Map.Entry::getKey).collect(Collectors.toList());
    }

    public void delete(long id) {
        DuplicateTopic dt=duplicateTopicRepository.findByDuplicate(id);
        if (dt!=null)
            duplicateTopicRepository.delete(dt);
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

    public List<DuplicateTopic> getDuplicates() {
        return (List<DuplicateTopic>)duplicateTopicRepository.findAll();
    }

    public void resolveDuplicate(long topicId) {
        DuplicateTopic dt = duplicateTopicRepository.findByDuplicate(topicId);
        duplicateTopicRepository.delete(dt);
    }
}
