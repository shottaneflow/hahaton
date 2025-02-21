package org.example.hahaton.entity;



import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;


@Entity
@Data
public class Teacher extends BaseEntity {


    private Boolean isActive;
    private String name;
    private String lastname;
    private Float baseRating;
    private Integer baseRatesCount;

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Grade> grades;

}
