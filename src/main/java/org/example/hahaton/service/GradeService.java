package org.example.hahaton.service;

import org.example.hahaton.dto.GradeDTO;
import org.example.hahaton.entity.Grade;

import java.util.List;

public interface GradeService {
    List<Grade> getGradesTeacher(String id);
    void addGrade(GradeDTO gradeDTO);
}
