package com.example.sms.controller;

import com.example.sms.entity.Student;
import com.example.sms.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // handler method to handle list students and return mode and view

    @GetMapping("/students")
    public String listStudents(Model model){
        model.addAttribute("students", studentService.getAllStudents());
        return "students";
    }

    @GetMapping("/students/new")
    public String createStudentForm(Model model){
        // create student object to hold student from data
        Student student = new Student();
        model.addAttribute("student", student);
        return "create_student";

    }

    @PostMapping("/students")
    public String saveStudent(@ModelAttribute("student") Student student){
        studentService.saveStudent(student);
        return "redirect:/students";
    }
    @GetMapping("/students/edit/{id}")
    public String editStudentForm(@PathVariable Long id, Model model){
        model.addAttribute("student", studentService.getStudentById(id));
        return "edit_student";
    }

    @PostMapping("/students/{id}")
    public String updateStudent(@PathVariable Long id, @ModelAttribute("student") Student student){
     //get student from database by id
        Student studentById = studentService.getStudentById(id);
        studentById.setFirstName(student.getFirstName());
        studentById.setLastName(student.getLastName());
        studentById.setEmail(student.getEmail());
        studentService.updateStudent(studentById);
        return "redirect:/students";
    }
    @GetMapping ("/delete/{id}")
    public String deleteStudent(@PathVariable Long id){
        studentService.deleteStudent(id);
        return "redirect:/students";
    }
}
