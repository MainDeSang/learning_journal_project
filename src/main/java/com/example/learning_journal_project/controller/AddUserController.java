package com.example.learning_journal_project.controller;

import com.example.learning_journal_project.model.Role;
import com.example.learning_journal_project.model.Topic;
import com.example.learning_journal_project.model.User;
import com.example.learning_journal_project.repository.TopicRepository;
import com.example.learning_journal_project.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;


@Controller
@RequestMapping("/users/addUser") //→ Mappt den Pfad /users/addUser auf die AddUserController-Klasse

public class AddUserController {

    private final TopicRepository topicRepository;
    private final UserRepository userRepository;


    public AddUserController(TopicRepository topicRepository, UserRepository userRepository) {
        this.topicRepository = topicRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("") // GET-Methode für die Seite "addUser"
    public String addUserPage(Model model) {
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("user", new User()); // Neues User-Objekt für das Formular
        model.addAttribute("topics", topicRepository.findAll()); // Alle Topics für das Formular
        return "addUser"; // Thymeleaf-Template "addUser.html"
    }


    @PostMapping("") // POST-Methode bleibt unter "/users/addUser"
    public String addUser(@ModelAttribute User user, @RequestParam(required = false) Set<Long> topicIds) {
        Set<Topic> selectedTopics = topicIds != null ? new HashSet<>(topicRepository.findAllById(topicIds)) : new HashSet<>();
        user.setTopics(selectedTopics);
        user.setRole(Role.USER); // Standardrolle für neue Benutzer: USER
        userRepository.save(user); // Benutzer in die Datenbank speichern
        return "redirect:/overview"; // Weiterleitung zur Benutzerübersicht
    }
}
