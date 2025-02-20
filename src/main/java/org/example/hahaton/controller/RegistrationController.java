package org.example.hahaton.controller;

import lombok.AllArgsConstructor;
import org.example.hahaton.entity.UserModel;
import org.example.hahaton.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reg-api")
public class RegistrationController {

    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> saveUser(@RequestBody UserModel user) {
        try {
            this.userService.addUser(user);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        catch (BadCredentialsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/activate/{code}")
    public ResponseEntity<?> activateUser(@PathVariable String code) {
        this.userService.activateUser(code);
        return new ResponseEntity<>("Activated", HttpStatus.OK);
    }
    @ExceptionHandler(BadCredentialsException.class)
    ResponseEntity<ProblemDetail> handleBadCredentialsException(BadCredentialsException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST,e.getLocalizedMessage()));
    }




}
