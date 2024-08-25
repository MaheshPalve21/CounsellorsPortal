package com.course.App.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.course.App.Entity.User;
import com.course.App.service.UserService;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam("email") String email, 
                            @RequestParam("password") String password, 
                            Model model) {
        // Check if user exists with given username and password
        User user = userService.findByEmailAndPassword(email, password);
        
        if (user != null) {
        	
            model.addAttribute("success", "Login successful!");
            return "redirect:/dashboard";   // Directly return the dashboard template
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }
    }
   
    
}

