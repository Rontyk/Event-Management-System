package com.example.concert.controllers;

import com.example.concert.dto.request.RegistrationRequestDTO;
import com.example.concert.security.UserDetails;
import com.example.concert.services.EventService;
import com.example.concert.services.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/registrations")
@RequiredArgsConstructor
public class RegistrationController {
    private final RegistrationService registrationService;
    private final EventService eventService;

    @ExceptionHandler
    private String handleException(Exception e){
        return "error";
    }

    @GetMapping
    public String Registration(Model model) {
        model.addAttribute("registration", new RegistrationRequestDTO());
        model.addAttribute("events", eventService.findAllEvents());
        return "registrations/index";
    }


    @GetMapping("/{id}")
    public String getRegistrationById(@PathVariable Long id, Model model) {
        model.addAttribute("registrationId", id);
        model.addAttribute("eventId", registrationService.findRegistrationById(id).getEvent().getEventId());
        return "registrations/change";
    }

    @GetMapping("/new")
    public String newRegistration(@RequestParam("eventId") Long eventId, Model model, Authentication authentication) {
        Long registerId = registrationService.checkToRegister(eventId, authentication);
        if(registerId != null){
            model.addAttribute("registerId", registerId);
            return "registrations/error";
        }
        model.addAttribute("eventId", eventId);
        return "registrations/index";
    }

    @PostMapping
    public String createRegistration(RegistrationRequestDTO registrationDto, Authentication authentication) {
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        registrationDto.setUserId(principal.getUser().getUserId());
        registrationService.saveRegistration(registrationDto);
        return "redirect:/events";
    }


    @PutMapping("/{id}")
    public String updateRegistration(@PathVariable Long id, @ModelAttribute RegistrationRequestDTO registrationDto, Authentication authentication) {
        registrationService.updateRegistration(id, registrationDto, authentication);
        return "redirect:/user/profile";
    }

    @DeleteMapping("/{id}")
    public String deleteRegistration(@PathVariable Long id) {
        registrationService.deleteRegistration(id);
        return "redirect:/user/profile";
    }
}
