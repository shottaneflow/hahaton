package org.example.hahaton.service;

import org.example.hahaton.entity.UserModel;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService  extends UserDetailsService {
    void addUser(UserModel user);
    void activateUser(String code);
    UserModel findByUsername(String username);
}
