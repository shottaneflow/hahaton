package org.example.hahaton.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class User extends BaseEntity {

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String activationCode;
    private boolean active;

    @ManyToMany
    @JoinTable(name="user_authority",
            joinColumns = @JoinColumn(name="id_user"),
            inverseJoinColumns = @JoinColumn(name="id_authority"))
    private List<Authority> authorities;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Grade> grades;
}
