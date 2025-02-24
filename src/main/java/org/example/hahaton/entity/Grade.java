package org.example.hahaton.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Grade extends BaseEntity {

    private Integer loyaltyGrade;

    private  Integer demandingGrade;

    private Integer materialPresentationGrade;

    private Integer senseOfHumorGrade;

    private Integer easeOfPassingMlt;

    private Integer generalImpressionMlt;

    private String comment;

    private Boolean isAnonim;

    private Boolean isModerated;

    @ManyToOne
    @JoinColumn(name="teacher_id")
    @JsonBackReference(value = "teacher-grades")
    private Teacher teacher;

    @ManyToOne
    @JoinColumn(name="user_id")
    @JsonBackReference(value = "user-grades")
    private UserModel userModel;
}
