package com.example.learning_journal_project.controller;

import com.example.learning_journal_project.model.Topic;
import com.example.learning_journal_project.model.User;
import com.example.learning_journal_project.service.TopicService;
import com.example.learning_journal_project.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;


@Controller
@RequestMapping("/topics") //→ Mappt den Pfad /topics auf die TopicController-Klasse
@Builder
@AllArgsConstructor

public class TopicController {
    public final TopicService topicService;
    public final UserService userService;


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

    @GetMapping("/addTopic")
    public String addTopic(Model model) {
        model.addAttribute("topics", topicService.getAllTopics());
        return "addTopic";
    }


    @PostMapping("/addTopic")
    public String addTopic(@ModelAttribute User user, @RequestParam(required = false) Set<Long> topicIds, @RequestParam(name = "title") String title, @RequestParam(name = "description") String description, @RequestParam(name = "duration") String duration) {
        Topic newTopic = Topic.builder().title(title).description(description).duration(duration).build();
        topicService.createTopic(newTopic);
        return "redirect:/topics";
    }

}
