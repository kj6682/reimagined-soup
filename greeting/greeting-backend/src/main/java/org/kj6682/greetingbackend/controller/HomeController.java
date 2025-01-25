package org.kj6682.greetingbackend.controller;

import org.kj6682.greetingbackend.entity.Greeting;
import org.kj6682.greetingbackend.repository.GreetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private GreetingRepository repository;

    @GetMapping("/")
    public String showHome(String name, Model model) {
        Greeting dockerGreeting = repository.findById(1).orElse(new Greeting("Not found \uD83D\uDE15"));
        model.addAttribute("name", dockerGreeting.getName());
        return "home";
    }

}
