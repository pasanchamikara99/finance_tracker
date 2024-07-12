package org.fianancetracker.backend.controllers;

import lombok.extern.slf4j.Slf4j;
import org.fianancetracker.backend.dto.UserDTO;
import org.fianancetracker.backend.service.UserService;
import org.fianancetracker.backend.util.MessageVarList;
import org.fianancetracker.backend.util.Validation;
import org.fianancetracker.backend.webToken.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Slf4j
public class LoginController {

    @Autowired
    UserService userService;

    JWTService jwtService = new JWTService();

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserDTO userDTO) {
        log.info("LoginController - register");
        String message = Validation.userRegistrationValidate(userDTO);
        if (!message.isEmpty()) {
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
        message = userService.registerUser(userDTO);
        if (message.isEmpty()) {
            return new ResponseEntity<>(MessageVarList.USER_REG_SUCCESS, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserDTO userDTO) {
        log.info("LoginController - login");
        String message = Validation.userLoginValidate(userDTO);
        if (!message.isEmpty()) {
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
        message = userService.loginUser(userDTO);
        if (message.isEmpty()) {
            return new ResponseEntity<>(jwtService.generateToken(userDTO), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }


}
