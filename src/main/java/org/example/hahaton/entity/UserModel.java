package org.example.hahaton.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class UserModel extends BaseEntity {

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String activationCode;
    private boolean active;

    public UserModel() {
        this.authorities = new ArrayList<>();
    }

    @ManyToMany
    @JoinTable(name="user_authority",
            joinColumns = @JoinColumn(name="id_user"),
            inverseJoinColumns = @JoinColumn(name="id_authority"))
    private List<Authority> authorities;

    @OneToMany(mappedBy = "userModel", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Grade> grades;

    public void addAuthorities(Authority authority) {
        this.authorities.add(authority);
    }
}
