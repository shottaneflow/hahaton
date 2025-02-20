package org.example.hahaton.entity;


import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

@MappedSuperclass
@Data
public abstract class BaseEntity {

    @Id
    @GeneratedValue(generator = "custom-UUID")
    @GenericGenerator(
            name = "custom-UUID",
            strategy = "org.example.hahaton.generator.UUIDGenerator"
    )
    private String id;

    @Column(name="created_at",updatable=false)
    private LocalDateTime createdAt;
    @Column(name="updated_at")
    private LocalDateTime updateAt;


}
