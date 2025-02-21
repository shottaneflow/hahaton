package org.example.hahaton.dto;

import java.util.UUID;
import lombok.Data;

@Data
public class GradeDTO {
    private String user_id;
    private String teacher_id;
    private Integer loyaltyGrade;
    private Integer demandingGrade;
    private Integer materialPresentationGrade;
    private Integer senseOfHumorGrade;
    private Integer easeOfPassingMlt;
    private Integer generalImpressionMlt;
    private String comment;
}
