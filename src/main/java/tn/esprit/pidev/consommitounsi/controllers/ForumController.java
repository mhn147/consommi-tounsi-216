package tn.esprit.pidev.consommitounsi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidev.consommitounsi.entities.forum.Post;
import tn.esprit.pidev.consommitounsi.entities.forum.Topic;
import tn.esprit.pidev.consommitounsi.services.forum.IPostService;
import tn.esprit.pidev.consommitounsi.services.forum.ITopicService;

import java.util.List;

@Controller
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
    public List<Topic> getAllTopics() {
        return topicService.getAllOrderedByDate();
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
    public List<Post> getAllPostsOrderedByDate(@PathVariable("topicId")long topicId) {
        return postService.getAllPostsOrderedByDate(topicId);
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
}
