package com.example.concert.controllers;

import com.example.concert.dto.request.UserRequestDTO;
import com.example.concert.entities.Registration;
import com.example.concert.entities.User;
import com.example.concert.security.UserDetails;
import com.example.concert.services.RegistrationService;
import com.example.concert.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final RegistrationService registrationService;

    @ExceptionHandler
    private String handleException(Exception e){
        return "error";
    }

    @GetMapping("/profile")
    public String getProfile(Model model, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userService.findUserById(userDetails.getUser().getUserId());
        List<Registration> registrations = registrationService.findRegistrationsByUserId(userDetails.getUser().getUserId());

        model.addAttribute("user", user);
        model.addAttribute("registrations", registrations);
        return "user/profile";
    }

}

