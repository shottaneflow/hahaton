package org.example.hahaton.service;

import org.example.hahaton.dto.GradeDTO;
import org.example.hahaton.entity.Grade;
import org.example.hahaton.entity.Teacher;
import org.example.hahaton.entity.UserModel;
import org.example.hahaton.repo.GradeRepo;
import org.example.hahaton.repo.TeacherRepo;
import org.example.hahaton.repo.UserRepo;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class DefGradeService implements GradeService {

    private final GradeRepo repo;
    private final UserRepo userRepo;
    private final TeacherRepo teacherRepo;
    public DefGradeService(GradeRepo repo,
                           UserRepo userRepo,
                           TeacherRepo teacherRepo) {
        this.repo = repo;
        this.userRepo = userRepo;
        this.teacherRepo = teacherRepo;
    }
    @Override
    public List<Grade> getGradesTeacher(String id) {
        return this.repo.findByTeacherId(id);
    }

    @Override
    public void addGrade(GradeDTO gradeDTO) {
        Teacher teacher = teacherRepo.findById(gradeDTO.getTeacher_id())
                .orElseThrow(() -> new RuntimeException("Teacher not found"));
        UserModel user = userRepo.findById(gradeDTO.getUser_id())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Grade grade = new Grade();
        grade.setTeacher(teacher);
        grade.setUserModel(user);
        grade.setLoyaltyGrade(gradeDTO.getLoyaltyGrade());
        grade.setDemandingGrade(gradeDTO.getDemandingGrade());
        grade.setMaterialPresentationGrade(gradeDTO.getMaterialPresentationGrade());
        grade.setSenseOfHumorGrade(gradeDTO.getSenseOfHumorGrade());
        grade.setEaseOfPassingMlt(gradeDTO.getEaseOfPassingMlt());
        grade.setGeneralImpressionMlt(gradeDTO.getGeneralImpressionMlt());
        grade.setComment(gradeDTO.getComment());


        repo.save(grade);
    }
}
