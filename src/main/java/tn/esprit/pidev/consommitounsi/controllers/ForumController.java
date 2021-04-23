package tn.esprit.pidev.consommitounsi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidev.consommitounsi.entities.forum.*;
import tn.esprit.pidev.consommitounsi.entities.user.User;
import tn.esprit.pidev.consommitounsi.services.forum.IPostService;
import tn.esprit.pidev.consommitounsi.services.forum.ITopicService;
import tn.esprit.pidev.consommitounsi.services.forum.TopicService;
import tn.esprit.pidev.consommitounsi.utils.UserSession;

import java.util.List;

@RestController
public class ForumController {
    @Autowired
    ITopicService topicService;
    @Autowired
    IPostService postService;

    @PostMapping("/customer/topics/{userId}")
    @ResponseBody
    public Topic addTopic(@RequestBody Topic t, @PathVariable("userId") long userId) {
        if (UserSession.hasId(userId) && !t.getTitle().isEmpty())
            return topicService.add(t, userId);
        return null;
    }

    @PostMapping("/customer/topics")
    @ResponseBody
    public void updateTopic(@RequestBody Topic t) {
        Topic topic=topicService.getById(t.getId());
        if (topic!=null && (UserSession.hasId(topic.getUser().getId()) || UserSession.isAdmin()) && !t.getTitle().isEmpty())
            topicService.update(t);
    }

    @GetMapping("/topics/{id}")
    @ResponseBody
    public Topic getTopicById(@PathVariable("id") long id) {
        return topicService.getById(id);
    }

    @GetMapping("/topics")
    @ResponseBody
    public List<Topic> getAllTopics(@RequestParam("sort")String sort) {
        List<Topic> topics=topicService.getAllOrderedByDate();
        if (sort.equals("popular"))
            return TopicService.orderByPopularity(topics,
                    new int[]{3,2,1},
                    t->(double)t.getStars().stream().mapToInt(Star::getValue).sum(),
                    t->(double)t.getPosts().size(),
                    t->t.getPosts().stream().mapToInt(p->p.getContent().length()).average().orElse(0));
        return topics;
    }

    @DeleteMapping("/customer/topics/{id}")
    @ResponseBody
    public void deleteTopic(@PathVariable("id") long id) {
        Topic t=topicService.getById(id);
        if (t!=null&&(UserSession.hasId(t.getUser().getId())||UserSession.isAdmin()))
            topicService.delete(id);
    }

    @PutMapping("/customer/topics/{topicId}/{userId}/{value}")
    @ResponseBody
    public void rateTopic(@PathVariable("topicId")long topicId, @PathVariable("userId")long userId, @PathVariable("value")int value) {
        if (UserSession.hasId(userId) && value>=1 && value<=5)
            topicService.rate(topicId, userId, value);
    }
  
    @GetMapping("/admin/topics/duplicates")
    @ResponseBody
    public List<DuplicateTopic> getDuplicates() {
        return topicService.getDuplicates();
    }

    @PutMapping("/admin/topics/{topicId}")
    @ResponseBody
    public void resolveDuplicate(@PathVariable("topicId") long topicId) {
        topicService.resolveDuplicate(topicId);
    }

    @PostMapping("/customer/posts/{userId}/{topicId}")
    @ResponseBody
    public void addPost(@RequestBody Post p,@PathVariable("userId") long userId,@PathVariable("topicId") long topicId) {
        if (UserSession.hasId(userId) && (!p.getContent().isEmpty() || !p.getMedias().isEmpty()))
            postService.addPost(p, userId, topicId);
    }

    @PostMapping("/customer/posts/edit")
    @ResponseBody
    public void updatePost(@RequestBody Post p) {
        Post post=postService.getPostById(p.getId());
        if (post!=null && (UserSession.hasId(post.getUser().getId())||UserSession.isAdmin()) && (!p.getContent().isEmpty() || !p.getMedias().isEmpty()))
            postService.updatePost(p);
    }

    @GetMapping("/posts/{id}")
    @ResponseBody
    public Post getPostById(@PathVariable("id") long id) {
        return postService.getPostById(id);
    }

    @GetMapping("/topics/{topicId}/posts")
    @ResponseBody
    public List<Post> getAllPostsByTopic(@PathVariable("topicId")long topicId, @RequestParam("sort") String sort) {
        List<Post> posts=postService.getAllPostsOrderedByDate(topicId);
        if (sort.equals("relevant"))
            return TopicService.orderByPopularity(posts,
                    new int[]{3,1,2},
                    p->(double)p.getLikes().stream().filter(PostLike::isLiked).count()/(p.getLikes().size()==0 ? 1 : (double)p.getLikes().size()),
                    p->(double)p.getLikes().size(),
                    p->(double)p.getContent().length());
        return posts;
    }

    @DeleteMapping("/customer/posts/{id}")
    @ResponseBody
    public void deletePost(@PathVariable("id") long id) {
        Post p = postService.getPostById(id);
        if (p!=null&&(UserSession.hasId(p.getUser().getId())||UserSession.isAdmin()))
            postService.deletePost(id);
    }

    @PutMapping("/customer/posts/{postId}/{userId}/{like}")
    @ResponseBody
    public void likePost(@PathVariable("postId")long postId, @PathVariable("userId")long userId, @PathVariable("like")boolean like) {
        if (UserSession.hasId(userId))
            postService.likePost(postId, userId, like);
    }

    @GetMapping("/admin/posts/forbidden")
    @ResponseBody
    public String getFobiddenWords() {
        return postService.getForbiddenWords();
    }

    @PostMapping("/admin/posts/forbidden")
    @ResponseBody
    public void setForbiddenWords(@RequestBody String forbiddenWords) {
        if (forbiddenWords==null)
            forbiddenWords="";
        postService.setForbiddenWords(forbiddenWords);
    }

    @GetMapping("/topics/users")
    @ResponseBody
    public List<User> getUserRanking() {
        return topicService.getUserRanking();
    }

    @GetMapping("/topics/{topicId}/users")
    @ResponseBody
    public List<User> getUserRanking(@PathVariable("topicId") long topicId) {
        return topicService.getUserRanking(topicId);
    }
}