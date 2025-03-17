package com.example.learning_journal_project.controller;

import com.example.learning_journal_project.model.User;
import com.example.learning_journal_project.repository.TopicRepository;
import com.example.learning_journal_project.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/users") //→ Mappt den Pfad /api/users auf die UserController-Klasse

public class UserController {

    private final UserService userService;
    private final TopicRepository topicRepository;


    public UserController(UserService userService, TopicRepository topicRepository) {
        this.userService = userService;
        this.topicRepository = topicRepository;
    }


    @GetMapping("")
    public String getUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("topics", topicRepository.findAll()); //Alle Topics in die View senden
        return "index";
    }


    @PostMapping("/delete/{userId}")
    public String deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return "redirect:/users";
    }

}

