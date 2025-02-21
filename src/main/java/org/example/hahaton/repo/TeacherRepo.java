package org.example.hahaton.repo;

import org.example.hahaton.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepo  extends JpaRepository<Teacher,String> {
    @Query("SELECT DISTINCT t FROM Teacher t WHERE LOWER(t.name) like  lower(concat('%',:keyword,'%') ) or " +
            "lower(t.lastname) like lower(concat('%',:keyword,'%') ) ")
    List<Teacher> searchTeachers(@Param("keyword") String keyword);


}
