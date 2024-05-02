package com.example.concert.controllers;

import com.example.concert.dto.request.EventRequestDTO;
import com.example.concert.services.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/events")
public class EventController {
    private final EventService eventService;

    @ExceptionHandler
    private String handleException(Exception e){
        return "error";
    }

    @GetMapping
    public String getAllEvents(Model model) {
        model.addAttribute("events", eventService.findAllEvents());
        return "events/index";
    }

    @GetMapping("/{id}")
    public String getEventById(@PathVariable Long id, Model model) {
        model.addAttribute("event", eventService.findEventById(id));
        return "events/show";
    }

    @PostMapping
    public ResponseEntity<?> createEvent(@RequestBody EventRequestDTO eventDto) {
        return ResponseEntity.ok(eventService.saveEvent(eventDto));
    }

}
