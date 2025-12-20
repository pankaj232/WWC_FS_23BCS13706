package com.example.mini_project.service;

import com.example.mini_project.model.Student;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StudentService {

    private final Map<Integer, Student> students = new HashMap<>();

    public boolean exists(int id) {
        return students.containsKey(id);
    }

    public Student save(Student student) {
        students.put(student.getId(), student);
        return student;
    }

    public List<Student> findAll() {
        return new ArrayList<>(students.values());
    }

    public Student findById(int id) {
        return students.get(id);
    }

    public boolean delete(int id) {
        return students.remove(id) != null;
    }
}
