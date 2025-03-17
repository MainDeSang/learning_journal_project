package com.example.learning_journal_project.controller;

import com.example.learning_journal_project.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("overview")
@Builder
@AllArgsConstructor
public class OverviewController {
    @Autowired // → Nur wenn der UserService-Bean angefordert wird, wird er angelegt.
    private final UserService userService;

    @GetMapping("")
    public String getUser(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "overview";
    }
}
