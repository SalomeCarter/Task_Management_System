package com.example.task_management_system.controller;

import com.example.task_management_system.dto.LoginUserDto;
import com.example.task_management_system.dto.RegUserDto;
import com.example.task_management_system.entity.SessionUser;
import com.example.task_management_system.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid RegUserDto regUserDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>("Validation failed", HttpStatus.BAD_REQUEST);
        }

        try {
            userService.save(regUserDto);
            return new ResponseEntity<>("Registration successful", HttpStatus.OK);
        } catch (ConstraintViolationException e) {
            return new ResponseEntity<>("The user with this username and email already exists", HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginUserDto loginUserDto,
                                        BindingResult bindingResult, HttpSession httpSession) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>("Validation failed", HttpStatus.BAD_REQUEST);
        }

        Optional<SessionUser> sessionUser = userService.login(loginUserDto);
        if (sessionUser.isPresent()) {
            httpSession.setAttribute("sessionUser", sessionUser.get());
            return new ResponseEntity<>("Login successful", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Email or password is invalid, please try again", HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/logout")
    public ResponseEntity<String> logout(HttpSession httpSession) {
        httpSession.invalidate();
        return new ResponseEntity<>("Logout successful", HttpStatus.OK);
    }
}

