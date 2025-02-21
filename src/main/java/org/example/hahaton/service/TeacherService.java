package org.example.hahaton.service;

import org.example.hahaton.entity.Teacher;

import java.util.List;
import java.util.Set;

public interface TeacherService {
    List<Teacher> getTeachers();
    Teacher getTeacher(String id);
    Teacher addTeacher(Teacher teacher);
    Set<Teacher> searchTeachers(String q);
    void updateTeacher(Teacher teacher);
}
