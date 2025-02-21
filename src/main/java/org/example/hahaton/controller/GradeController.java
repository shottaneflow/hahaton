package org.example.hahaton.controller;


import org.example.hahaton.dto.GradeDTO;
import org.example.hahaton.entity.Grade;
import org.example.hahaton.service.GradeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grade-api")
public class GradeController {

    private GradeService gradeService;
    public GradeController(GradeService gradeService) {
        this.gradeService = gradeService;
    }
    @GetMapping()
    public ResponseEntity<?> getGradesTeach(@RequestParam String id){
        List<Grade> g=this.gradeService.getGradesTeacher(id);
        return ResponseEntity.ok(g);
    }
    @PostMapping("/new")
    public ResponseEntity<?> addGrade(@RequestBody GradeDTO gradeDTO){
        gradeService.addGrade(gradeDTO);
        return ResponseEntity.ok().build();
    }

}
