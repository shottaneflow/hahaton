package org.example.hahaton.service;

import org.example.hahaton.entity.UserModel;
import org.example.hahaton.repo.UserRepo;
import org.example.hahaton.utils.SmtpMailSender;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.example.hahaton.entity.Authority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DefUserService implements UserService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final SmtpMailSender smtpMailSender;
    public DefUserService(UserRepo userRepo,
                          SmtpMailSender smtpMailSender) {
        this.userRepo = userRepo;
        this.passwordEncoder = new BCryptPasswordEncoder();
        this.smtpMailSender = smtpMailSender;
    }
    @Override
    public UserDetails loadUserByUsername(String username)  {
        UserModel userModel =this.userRepo.findByUsername(username)
                 .orElseThrow(()->new UsernameNotFoundException("Пользователь "+username+" не найден"));
        return new User(
                userModel.getUsername(),
                userModel.getPassword(),
                userModel.getAuthorities().stream()
                        .map(Authority::getAuthority)
                        .map(SimpleGrantedAuthority::new)
                        .toList());
    }

    @Override
    public void addUser(UserModel user) {
        UserModel userModel=this.userRepo.findByUsername(user.getUsername()).orElse(null);
        if(userModel != null) {
            throw new BadCredentialsException("Пользователь уже существует");
        }

        user.setActive(true);
        Authority auth = new Authority();
        auth.setId(2);
        auth.setAuthority("ROLE_USER");
        user.addAuthorities(auth);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActivationCode(UUID.randomUUID().toString());
        this.userRepo.save(user);

        if (!StringUtils.isEmpty(user.getEmail())) {
            String message = String.format(
                    "Hi, %s! \n" +
                            "Welcome to hahatonApp. Please, visit next link to confirm your email : " +
                            "http://localhost:8080/reg-api/activate/%s",
                    user.getUsername(),
                    user.getActivationCode()
            );
            smtpMailSender.send(user.getEmail(), "Activation Code", message);
        }
    }

    @Override
    public void activateUser(String code) {
        UserModel user=this.userRepo.findByActivationCode(code);

        if(user==null){
            throw new BadCredentialsException("Не найден код активации");
        }
        user.setActivationCode(null);
        userRepo.save(user);
    }
    @Override
    public UserModel findByUsername(String username) {
        return this.userRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь "+username+" не найден"));
    }
}
