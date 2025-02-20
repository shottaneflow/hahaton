package org.example.hahaton.controller;


import org.example.hahaton.dto.AuthRequest;
import org.example.hahaton.entity.UserModel;
import org.example.hahaton.exception.AppError;
import org.example.hahaton.service.UserService;
import org.example.hahaton.utils.JwtTokenUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth-api")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtTokenUtils jwtTokenUtils;

    public AuthController(AuthenticationManager authenticationManager,
                          UserService userService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtTokenUtils = new JwtTokenUtils();
    }
    @PostMapping
    public ResponseEntity<?> auth(@RequestBody UserModel userModel) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userModel.getUsername(),
                    userModel.getPassword()));
            UserModel user=this.userService.findByUsername(userModel.getUsername());
            //Проверяю, если юзер подтвердит почту, то activationCode скинется в null,
            //если нет, то он не даст зайти, потому что бзер добавится в бд, но activationCode будет висеть UUID
            if(!StringUtils.isEmpty(user.getActivationCode())){
                return new ResponseEntity<>(new AppError(HttpStatus.CONFLICT.value(),"Подтвердите email"), HttpStatus.CONFLICT);
            }
        }
        catch (BadCredentialsException e) {
            if(this.userService.findByUsername(userModel.getUsername()) != null)
                return new ResponseEntity<>("Неправильный пароль", HttpStatus.BAD_REQUEST);
        }
        UserDetails userDetails=userService.loadUserByUsername(userModel.getUsername());
        String token=jwtTokenUtils.generateToken(userDetails);
        AuthRequest authRequest=new AuthRequest();
        authRequest.setToken(token);
        authRequest.setRoles(userDetails.getAuthorities());

        return ResponseEntity.ok(authRequest);

    }
}
