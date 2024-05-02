package com.example.concert.controllers;

import com.example.concert.dto.request.UserRequestDTO;
import com.example.concert.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @ExceptionHandler
    private String handleException(Exception e){
        return "error";
    }

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }

    @PostMapping("/registration")
    public String registrationPage(@ModelAttribute UserRequestDTO userDto){
        userService.saveUser(userDto);
        return "redirect:/events";
    }

    @GetMapping
    public String mainPage(Authentication authentication){
        if(userService.checkToAuth(authentication)){
            return "index";
        }
        return "show";
    }
}
