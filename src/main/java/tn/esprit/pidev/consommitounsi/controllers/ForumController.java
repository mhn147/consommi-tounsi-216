package tn.esprit.pidev.consommitounsi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidev.consommitounsi.entities.forum.Topic;
import tn.esprit.pidev.consommitounsi.services.forum.ITopicService;

import java.util.List;

@Controller
public class ForumController {
    @Autowired
    ITopicService topicService;

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
}
