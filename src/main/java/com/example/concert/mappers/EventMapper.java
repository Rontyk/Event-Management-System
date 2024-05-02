package com.example.concert.mappers;

import com.example.concert.dto.response.EventResponseDTO;
import com.example.concert.entities.Event;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EventMapper extends BaseMapper<Event, EventResponseDTO> {
    @Override
    default EventResponseDTO toDto(Event entity){
        return EventResponseDTO.builder()
                .eventId(entity.getEventId())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .eventDate(entity.getEventDate())
                .location(entity.getLocation())
                .availableSeats(entity.getAvailableSeats())
                .build();
    }

}