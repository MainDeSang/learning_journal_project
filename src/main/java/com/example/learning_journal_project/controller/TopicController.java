package com.example.learning_journal_project.controller;
import com.example.learning_journal_project.model.Topic;
import com.example.learning_journal_project.service.TopicService;
import com.example.learning_journal_project.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/topics") //→ Mappt den Pfad /topics auf die TopicController-Klasse

public class TopicController {

    public final TopicService topicService;
    public final UserService userService;

    public TopicController(TopicService topicService, UserService userService) {
        this.topicService = topicService;
        this.userService = userService;
    }

    @GetMapping("")
    public String getTopics(Model model) {
        List<Topic> topics = topicService.getAllTopics();
        model.addAttribute("topics", topics);
        return "topics";
    }

    @PostMapping("/delete/{topicId}")
    public String deleteTopic(@PathVariable Long topicId) {
        topicService.deleteTopic(topicId);
        return "redirect:/topics";
    }

    @GetMapping("/add")
    public String addTopicPage() {
        return "addTopic";
    }
    @PostMapping("/addTopic")
    public String addTopic(@RequestParam(name = "title") String title, @RequestParam(name = "description") String description, @RequestParam(name = "duration") String duration) {
        topicService.createTopic(new Topic(null, title, description, duration));
        return "redirect:/topics";
    }

}
