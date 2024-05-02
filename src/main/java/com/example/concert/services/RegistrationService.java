package com.example.concert.services;

import com.example.concert.dto.request.RegistrationRequestDTO;
import com.example.concert.entities.Event;
import com.example.concert.entities.Registration;
import com.example.concert.repositories.EventRepository;
import com.example.concert.repositories.RegistrationRepository;
import com.example.concert.repositories.UserRepository;
import com.example.concert.security.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RegistrationService {
    private final RegistrationRepository registrationRepository;
    private final EventRepository eventRepository;

    private final UserRepository userRepository;

    @Autowired
    public RegistrationService(RegistrationRepository registrationRepository, EventRepository eventRepository, UserRepository userRepository) {
        this.registrationRepository = registrationRepository;
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }

    public List<Registration> findAllRegistrations(){
        return registrationRepository.findAll();
    }

    public Registration findRegistrationById(Long id) {
        return registrationRepository.findById(id).orElse(null);
    }

    public void create(){

    }

    public List<Registration> findRegistrationsByUserId(Long id){
        return registrationRepository.findRegistrationsByUserUserId(id);
    }

    public Long checkToRegister(Long eventId, Authentication authentication){
        Registration registration = registrationRepository.findRegistrationByUserUserIdAndEventEventId(((UserDetails)authentication.getPrincipal()).getUser().getUserId(), eventId);
        if(registration == null)
            return null;
        return registration.getRegistrationId();
    }

    public Registration saveRegistration(RegistrationRequestDTO registrationDto) {
        System.out.println(registrationDto);
        Event event = eventRepository.getEventByEventId(registrationDto.getEventId());
        int wantToTakeSeats = registrationDto.getSeats();
        if (event.getAvailableSeats() < wantToTakeSeats)
            throw new RuntimeException("We have no such more tickets");
        event.setAvailableSeats(event.getAvailableSeats() - wantToTakeSeats);
        eventRepository.save(event);
        Registration registration = new Registration();
        registration.setUser(userRepository.getUserByUserId(registrationDto.getUserId()));
        registration.setEvent(eventRepository.getEventByEventId(registrationDto.getEventId()));
        registration.setSeats(wantToTakeSeats);
        return registrationRepository.save(registration);
    }

    public void deleteRegistration(Long id) {
        Registration registration = registrationRepository.getRegistrationByRegistrationId(id);
        Event event = registration.getEvent();
        event.setAvailableSeats(event.getAvailableSeats() + registration.getSeats());
        registrationRepository.deleteById(id);
        eventRepository.save(event);
    }

    public void updateRegistration(Long registrationId, RegistrationRequestDTO registrationUpdates, Authentication authentication) {
        System.out.println(registrationUpdates);
        Event event = eventRepository.findById(registrationUpdates.getEventId())
                .orElseThrow(() -> new RuntimeException("Event not found"));
        Registration registration = registrationRepository.findById(registrationId)
                .orElseThrow(() -> new RuntimeException("Registration not found"));

        if(((UserDetails) authentication.getPrincipal()).getUser().getUserId() != registration.getUser().getUserId())
            throw new RuntimeException("You cannot change other user's order");
        if (registrationUpdates.getSeats() == 0)
            throw new RuntimeException("You cannot get 0 tickets");
        if (registration.getSeats().equals(registrationUpdates.getSeats()))
            throw new RuntimeException("Enter different number of tickets");
        int seatChange = registrationUpdates.getSeats() - registration.getSeats();
        if (event.getAvailableSeats() < Math.abs(seatChange))
            throw new RuntimeException("Not enough tickets available");
        event.setAvailableSeats(event.getAvailableSeats() - seatChange);
        registration.setSeats(registrationUpdates.getSeats());

        eventRepository.save(event);
        registrationRepository.save(registration);
    }


}