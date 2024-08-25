package com.course.App.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.course.App.service.StudentService;
import com.course.App.service.UserService;

@Controller
public class DashboardController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private UserService userService;

    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        // Fetch the required counts from the service
        long totalStudents = studentService.getTotalStudentsCount();
        long newStudents = studentService.getNewStudentsCount();
        long enrolledStudents = studentService.getEnrolledStudentsCount();
        long lostStudents = studentService.getLostStudentsCount();

        // Add the data to the model
        model.addAttribute("totalStudents", totalStudents);
        model.addAttribute("newStudents", newStudents);
        model.addAttribute("enrolledStudents", enrolledStudents);
        model.addAttribute("lostStudents", lostStudents);

        return "dashboard";
    }
}

