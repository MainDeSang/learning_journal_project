package com.example.learning_journal_project.controller;
import com.example.learning_journal_project.model.Topic;
import com.example.learning_journal_project.model.User;
import com.example.learning_journal_project.repository.TopicRepository;
import com.example.learning_journal_project.service.TopicService;
import com.example.learning_journal_project.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Controller
@RequestMapping("/topics") //→ Mappt den Pfad /topics auf die TopicController-Klasse

public class TopicController {

    public final TopicService topicService;
    public final UserService userService;
    private final TopicRepository topicRepository;

    public TopicController(TopicService topicService, UserService userService, TopicRepository topicRepository) {
        this.topicService = topicService;
        this.userService = userService;
        this.topicRepository = topicRepository;
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
    public String addTopic(@ModelAttribute User user, @RequestParam(required = false) Set<Long> topicIds) {
        Set<Topic> selecteTopics = new HashSet<>(topicRepository.findAllById(topicIds));
        user.setTopics(selecteTopics);
        userService.createUser(user);
        return "redirect:/topics";
    }

}
