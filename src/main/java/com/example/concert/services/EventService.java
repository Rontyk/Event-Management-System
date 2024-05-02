package com.example.concert.services;

import com.example.concert.dto.request.EventRequestDTO;
import com.example.concert.entities.Event;
import com.example.concert.repositories.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EventService {
    private final EventRepository eventRepository;
    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<Event> findAllEvents() {
        return eventRepository.findAll();
    }

    public Event findEventById(Long id) {
        return eventRepository.findById(id).orElse(null);
    }

    public Event saveEvent(EventRequestDTO eventDTO) {
        Event event = new Event();
        event.setEventDate(eventDTO.getEventDate());
        event.setDescription(eventDTO.getDescription());
        event.setLocation(eventDTO.getLocation());
        event.setTitle(eventDTO.getTitle());
        event.setAvailableSeats(eventDTO.getAvailableSeats());
        return eventRepository.save(event);
    }

    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }

    public Event updateEvent(Long id, EventRequestDTO eventUpdates) {
        return eventRepository.findById(id)
                .map(event -> {
                    event.setTitle(eventUpdates.getTitle());
                    event.setDescription(eventUpdates.getDescription());
                    event.setEventDate(eventUpdates.getEventDate());
                    event.setLocation(eventUpdates.getLocation());
                    event.setAvailableSeats(eventUpdates.getAvailableSeats());
                    return eventRepository.save(event);
                }).orElseThrow(() -> new RuntimeException("Event not found with id " + id));
    }

}

