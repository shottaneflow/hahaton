package org.example.hahaton.service;

import org.example.hahaton.entity.Teacher;
import org.example.hahaton.repo.TeacherRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class DefTeacherService implements TeacherService {

    private final TeacherRepo teacherRepo;

    public DefTeacherService(TeacherRepo teacherRepo) {
        this.teacherRepo = teacherRepo;
    }

    @Override
    public List<Teacher> getTeachers() {
        return teacherRepo.findAll();
    }

    @Override
    public Teacher getTeacher(String id) {
        return teacherRepo.findById(id).orElse(null);
    }

    @Override
    public Teacher addTeacher(Teacher teacher) {
        return teacherRepo.save(teacher);
    }

    @Override
    public Set<Teacher> searchTeachers(String q) {
        String []keywords = q.split(" ");
        Set<Teacher> teachers = new HashSet<>();
        for (String keyword : keywords) {
            List<Teacher> teacherList = teacherRepo.searchTeachers(keyword);
            for (Teacher teacher : teacherList) {
                teachers.add(teacher);
            }
        }
        return teachers;
    }

    @Override
    public void updateTeacher(Teacher teacher) {
        this.teacherRepo.save(teacher);
    }


}
