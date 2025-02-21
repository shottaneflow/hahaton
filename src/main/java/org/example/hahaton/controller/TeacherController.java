package org.example.hahaton.controller;

import org.example.hahaton.entity.Teacher;
import org.example.hahaton.service.TeacherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@RestController
@RequestMapping("/user-api/teachers")
public class TeacherController {

    private TeacherService teacherService;
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllTeachers() {
        List<Teacher> t=this.teacherService.getTeachers();
        return ResponseEntity.ok(t);
    }
    @GetMapping
    public ResponseEntity<?> searchTeachers(@RequestParam String q) {
        Set<Teacher> set=new HashSet<>();
        set.addAll(teacherService.getTeachers());
        return ResponseEntity.ok(set);

    }
}
