package tn.esprit.pidev.consommitounsi.controllers;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidev.consommitounsi.entities.forum.Post;
import tn.esprit.pidev.consommitounsi.entities.forum.PostLike;
import tn.esprit.pidev.consommitounsi.entities.forum.Star;
import tn.esprit.pidev.consommitounsi.entities.forum.Topic;
import tn.esprit.pidev.consommitounsi.services.forum.IPostService;
import tn.esprit.pidev.consommitounsi.services.forum.ITopicService;
import tn.esprit.pidev.consommitounsi.services.forum.TopicService;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

@RestController
public class ForumController {
    @Autowired
    ITopicService topicService;
    @Autowired
    IPostService postService;

    @PostMapping("/topics/{userId}")
    @ResponseBody
    public void addTopic(@RequestBody Topic t, @PathVariable("userId") long userId) {
        topicService.add(t, userId);
    }

    @PostMapping("/topics")
    @ResponseBody
    public void updateTopic(@RequestBody Topic t) {
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

    @DeleteMapping("/topics/{id}")
    @ResponseBody
    public void deleteTopic(@PathVariable("id") long id) {
        topicService.delete(id);
    }

    @PutMapping("/topics/{topicId}/{userId}/{value}")
    @ResponseBody
    public void rateTopic(@PathVariable("topicId")long topicId, @PathVariable("userId")long userId, @PathVariable("value")int value) {
        topicService.rate(topicId, userId, value);
    }

    @PostMapping("/posts/{userId}/{topicId}")
    @ResponseBody
    public void addPost(@RequestBody Post p,@PathVariable("userId") long userId,@PathVariable("topicId") long topicId) {
        postService.addPost(p, userId, topicId);
    }

    @PostMapping("/posts/edit")
    @ResponseBody
    public void updatePost(@RequestBody Post p) {
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

    @DeleteMapping("/posts/{id}")
    @ResponseBody
    public void deletePost(@PathVariable("id") long id) {
        postService.deletePost(id);
    }

    @PutMapping("/posts/{postId}/{userId}/{like}")
    @ResponseBody
    public void likePost(@PathVariable("postId")long postId, @PathVariable("userId")long userId, @PathVariable("like")boolean like) {
        postService.likePost(postId, userId, like);
    }

    @GetMapping("/posts/forbidden")
    @ResponseBody
    public String getFobiddenWords() {
        return postService.getForbiddenWords();
    }

    @PostMapping("/posts/forbidden")
    @ResponseBody
    public void setForbiddenWords(@RequestBody String forbiddenWords) {
        if (forbiddenWords==null)
            forbiddenWords="";
        postService.setForbiddenWords(forbiddenWords);
    }
}