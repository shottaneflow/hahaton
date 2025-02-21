package org.example.hahaton.controller;


import org.example.hahaton.entity.Teacher;
import org.example.hahaton.service.TeacherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin-api/teachers")
public class AdminTeacherController {

    private final TeacherService teacherService;
    public AdminTeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createTeacher(@RequestBody Teacher teacher) {
        Teacher t=this.teacherService.addTeacher(teacher);
        return ResponseEntity.ok().body(t.getId());
    }
    @PutMapping("{teacherId}/update")
    public ResponseEntity<?> updateTeacher(@RequestBody Teacher teacher,
                                           @PathVariable String teacherId) {
        teacher.setId(teacherId);
        this.teacherService.updateTeacher(teacher);
        return ResponseEntity.ok().build();
    }
}
