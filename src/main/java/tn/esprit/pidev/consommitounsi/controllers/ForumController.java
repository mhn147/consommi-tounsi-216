package tn.esprit.pidev.consommitounsi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidev.consommitounsi.entities.forum.Post;
import tn.esprit.pidev.consommitounsi.entities.forum.Topic;
import tn.esprit.pidev.consommitounsi.services.forum.IPostService;
import tn.esprit.pidev.consommitounsi.services.forum.ITopicService;
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
    public void addTopic(@RequestBody Topic t, @PathVariable("userId") long userId) {
        if (UserSession.hasId(userId))
            topicService.add(t, userId);
    }

    @PostMapping("/customer/topics")
    @ResponseBody
    public void updateTopic(@RequestBody Topic t) {
        Topic topic=topicService.getById(t.getId());
        if (topic!=null&&(UserSession.hasId(topic.getUser().getId())||UserSession.isAdmin()))
            topicService.update(t);
    }

    @GetMapping("/topics/{id}")
    @ResponseBody
    public Topic getTopicById(@PathVariable("id") long id) {
        return topicService.getById(id);
    }

    @GetMapping("/topics")
    @ResponseBody
    public List<Topic> getAllTopics() {
        return topicService.getAllOrderedByDate();
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
        topicService.rate(topicId, userId, value);
    }

    @PostMapping("/customer/posts/{userId}/{topicId}")
    @ResponseBody
    public void addPost(@RequestBody Post p,@PathVariable("userId") long userId,@PathVariable("topicId") long topicId) {
        if (UserSession.hasId(userId))
            postService.addPost(p, userId, topicId);
    }

    @PostMapping("/customer/posts/edit")
    @ResponseBody
    public void updatePost(@RequestBody Post p) {
        Post post=postService.getPostById(p.getId());
        if (post!=null && (UserSession.hasId(post.getUser().getId())||UserSession.isAdmin()))
            postService.updatePost(p);
    }

    @GetMapping("/posts/{id}")
    @ResponseBody
    public Post getPostById(@PathVariable("id") long id) {
        return postService.getPostById(id);
    }

    @GetMapping("/topics/{topicId}/posts")
    @ResponseBody
    public List<Post> getAllPostsOrderedByDate(@PathVariable("topicId")long topicId) {
        return postService.getAllPostsOrderedByDate(topicId);
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
        postService.likePost(postId, userId, like);
    }
}
