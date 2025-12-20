package com.example.mini_project.controller;

import com.example.mini_project.model.Student;
import com.example.mini_project.service.StudentService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> register(@RequestBody Student student) {

        if (student.getName() == null || student.getName().isBlank()
                || student.getCourse() == null || student.getCourse().isBlank()) {
            return ResponseEntity.badRequest().body("Name and course must not be empty");
        }

        if (service.exists(student.getId())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Student with ID already exists");
        }

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.save(student));
    }

    @GetMapping
    public List<Student> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable int id) {
        Student student = service.findById(id);
        if (student == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Student not found");
        }
        return ResponseEntity.ok(student);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        if (!service.delete(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Student not found");
        }
        return ResponseEntity.ok("Student deleted successfully");
    }
}
