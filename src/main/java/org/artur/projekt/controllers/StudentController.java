package org.artur.projekt.controllers;

import jakarta.validation.Valid;
import org.artur.projekt.models.Student;
import org.artur.projekt.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/add")
    public String add(Model model){
        model.addAttribute("student", new Student());
        return "addStudent";
    }

    @PostMapping("/add")
    public String addSubmit(@Valid @ModelAttribute Student student, BindingResult result){
        if(result.hasErrors()){
            return "addStudent";
        }
        studentService.saveStudent(student);
        return "redirect:/students/result";
    }

    @GetMapping("/result")
    public String result(Model model){
        List<Student> students = studentService.getAllStudents();
        model.addAttribute("students", students);
        return "result";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("studentsId") long id){
        studentService.removeStudentById(id);
        return "redirect:/students/result";
    }

    @GetMapping("/update")
    public String getUpdate(@RequestParam("studentsId") long id, Model model){
        Optional<Student> foundStudent = studentService.findStudentById(id);
        if(foundStudent.isPresent()) {
            model.addAttribute("student", foundStudent.get());
            return "updateStudent";
        }
        return "redirect:/students/result";
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute ("student") Student student, BindingResult result){
        if(result.hasErrors()){
            return "updateStudent";
        }
        studentService.saveStudent(student);
        return "redirect:/students/result";
    }
}
