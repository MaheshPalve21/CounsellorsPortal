package com.course.App.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


import com.course.App.Entity.User;
import com.course.App.service.UserService;

	@Controller
	public class RegistrationController {

	    @Autowired
	    private UserService userService;

	    @GetMapping("/register")
	    public String showRegistrationForm(Model model) {
	        model.addAttribute("user", new User());
	        return "register";
	    }

	    @PostMapping("/register")
	    public String registerUser(@ModelAttribute("user") User user, Model model) {
	        userService.save(user);
	        model.addAttribute("message", "User registered successfully!");
	        return "register";
	    }
	}


