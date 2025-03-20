package com.example.learning_journal_project.controller;

import com.example.learning_journal_project.model.Role;
import com.example.learning_journal_project.model.Topic;
import com.example.learning_journal_project.model.User;
import com.example.learning_journal_project.model.Vintage;
import com.example.learning_journal_project.repository.TopicRepository;
import com.example.learning_journal_project.repository.UserRepository;
import com.example.learning_journal_project.repository.VintageRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Controller
@RequestMapping("/users/addUser") //→ Mappt den Pfad /users/addUser auf die AddUserController-Klasse

public class AddUserController {

    private final TopicRepository topicRepository;
    private final UserRepository userRepository;
    private final VintageRepository vintageRepository;

    public AddUserController(TopicRepository topicRepository, UserRepository userRepository, VintageRepository vintageRepository) {
        this.topicRepository = topicRepository;
        this.userRepository = userRepository;
        this.vintageRepository = vintageRepository;
    }

    @GetMapping("") // GET-Methode für die Seite "addUser"
    public String addUserPage(Model model) {
        if (vintageRepository.count() == 0) {
            vintageRepository.saveAll(List.of(new Vintage("2024"), new Vintage("2025")));
        }

        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("user", new User()); // Neues User-Objekt für das Formular
        model.addAttribute("topics", topicRepository.findAll()); // Alle Topics für das Dropdown-Menü
        model.addAttribute("vintages", vintageRepository.findAll()); // Alle Jahrgänge für das Dropdown-Menü
        return "addUser"; // Thymeleaf-Template "addUser.html"
    }


//    @PostMapping("") // POST-Methode bleibt unter "/users/addUser"
//    public String addUser(@ModelAttribute User user, @RequestParam(required = false) Set<Long> topicIds, @RequestParam(required = false) Long vintageIds) {
//        Set<Topic> selectedTopics = topicIds != null ? new HashSet<>(topicRepository.findAllById(topicIds)) : new HashSet<>();
//        user.setTopics(selectedTopics);
//        user.setRole(Role.USER); // Standardrolle für neue Benutzer: USER
//        Vintage vintage = vintageRepository.findById(1L).orElse(null); // Hole den ersten Jahrgang aus der Datenbank (Standardwert: 2024)
//        user.setVintage(vintage);
//        userRepository.save(user); // Benutzer in die Datenbank speichern
//        return "redirect:/overview"; // Weiterleitung zur Benutzerübersicht
//    }

    @PostMapping("")
    public String addUser(@ModelAttribute User user,
                          @RequestParam(required = false) Set<Long> topicIds,
                          @RequestParam(required = false) Long vintageId) { // Richtig: "vintageId"
        Set<Topic> selectedTopics = topicIds != null ? new HashSet<>(topicRepository.findAllById(topicIds)) : new HashSet<>();
        user.setTopics(selectedTopics);
        user.setRole(Role.USER);

        // Hole den Jahrgang basierend auf der ID aus dem Request
        Vintage vintage = vintageId != null ? vintageRepository.findById(vintageId).orElse(null) : null;
        user.setVintage(vintage);

        userRepository.save(user);
        return "redirect:/overview";
    }

}
