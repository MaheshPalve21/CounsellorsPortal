package com.course.App.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.course.App.Entity.Student;
import com.course.App.Entity.User;
import com.course.App.service.StudentService;
import com.course.App.service.UserService;

@Controller
public class StudentController {

    @Autowired
    private UserService userService;

    @Autowired
    private StudentService studentService;

    @GetMapping("/student")
    public String showStudentForm(Model model) {
        Student student = new Student();
        student.setCourseStatus("New");  // Set default course status
        model.addAttribute("student", student);
        return "student";
    }


    @PostMapping("/student")
    public String registerStudent(@ModelAttribute("student") Student student, 
                                  @RequestParam("userId") Long userId,
                                  Model model) {
        User user = userService.findById(userId);
        student.setUser(user);
        studentService.saveStudent(student);
        model.addAttribute("message", "Student registered successfully!");
        return "student";
    }
    
   
    @GetMapping("/students")
    public String listStudents(@RequestParam(required = false) String courseFilter,
            @RequestParam(required = false) String modeFilter,
            @RequestParam(required = false) String statusFilter,
            Model model) {
                               
        // Fetch data with or without filters
        List<Student> students;
        if ( courseFilter == null && modeFilter == null && statusFilter == null) {
            // No filters, fetch all students
            students = studentService.getAllStudents();
        } else {
            // Apply filters
            students = studentService.getFilteredStudents( courseFilter, modeFilter, statusFilter);
        }

        // Add data to the model
        model.addAttribute("students", students);
        model.addAttribute("courseFilter", courseFilter);
        model.addAttribute("modeFilter", modeFilter);
        model.addAttribute("statusFilter", statusFilter);
        return "students";
    }
        @GetMapping("/students/edit/{id}")
        public String editStudent(@PathVariable Long id, Model model) {
            Student student = studentService.findById(id);
            model.addAttribute("student", student);
            return "editstudent";
        }

        @PostMapping("/students/update")
        public String updateStudent(@ModelAttribute("student") Student student) {
            studentService.saveStudent(student);
            return "redirect:/students";
        }
    }



