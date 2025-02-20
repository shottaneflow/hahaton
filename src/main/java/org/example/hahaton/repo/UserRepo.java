package org.example.hahaton.repo;

import org.example.hahaton.entity.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<UserModel,String> {
    Optional<UserModel> findByUsername(String username);
    UserModel findByActivationCode(String code);
}
