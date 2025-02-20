package org.example.hahaton.entity;

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

    @ManyToOne
    @JoinColumn(name="teacher_id")
    private Teacher teacher;

    @ManyToOne
    @JoinColumn(name="user_id")
    private UserModel userModel;
}
