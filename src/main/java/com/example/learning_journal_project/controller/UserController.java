package com.example.learning_journal_project.controller;

import com.example.learning_journal_project.model.Role;
import com.example.learning_journal_project.model.User;
import com.example.learning_journal_project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/users") //→ Mappt den Pfad /api/users auf die UserController-Klasse
@RestController //→ Markiert diese Klasse als ein REST-Controller, die JSON-Rückgaben liefert
@CrossOrigin //→ Löst Cross-Origin-Resource-Sharing-Anfragen (CORS) aus
@RequiredArgsConstructor //→ Konstruktorautowiring für private final Felder

public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    public String getUser(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users";
    }


    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PostMapping("/add")
    public String addUser(@RequestParam(name = "fullName") String fullName, @RequestParam(name = "email") String email, @RequestParam(name = "password") String password)  {
        userService.createUser(new User(null, fullName, email, password, Role.USER));
        return "redirect:/users";
    }

    @DeleteMapping("/users/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
    }

}

