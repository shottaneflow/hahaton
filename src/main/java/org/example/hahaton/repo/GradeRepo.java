package org.example.hahaton.repo;


import org.example.hahaton.entity.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GradeRepo extends JpaRepository<Grade, String> {
    List<Grade> findByTeacherId(String teacherId);
}
