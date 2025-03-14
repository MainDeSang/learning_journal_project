package com.example.learning_journal_project.controller;

import com.example.learning_journal_project.model.Role;
import com.example.learning_journal_project.model.User;
import com.example.learning_journal_project.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/users/addUser") //→ Mappt den Pfad /users/addUser auf die AddUserController-Klasse

public class AddUserController {

    private final UserService userService;


    public AddUserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String addUserPage() {
        return "addUser";
    }

    @PostMapping("")
    public String addUser(@RequestParam(name = "fullName") String fullName, @RequestParam(name = "email") String email, @RequestParam(name = "password") String password) {
        userService.createUser(new User(null, fullName, email, password, Role.USER));
        return "redirect:/users";
    }
}
