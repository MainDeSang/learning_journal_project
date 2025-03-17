package com.example.learning_journal_project.controller;

import com.example.learning_journal_project.repository.TopicRepository;
import com.example.learning_journal_project.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/users") //→ Mappt den Pfad /users auf die UserController-Klasse

public class UserController {

    private final UserService userService;


    public UserController(UserService userService, TopicRepository topicRepository) {
        this.userService = userService;
    }


    @GetMapping("")
    public String getUser(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "index";
    }


    @PostMapping("/delete/{userId}")
    public String deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return "redirect:/overview";
    }

}

