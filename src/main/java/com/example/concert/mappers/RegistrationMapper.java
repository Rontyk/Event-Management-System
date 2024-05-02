package com.example.concert.mappers;

import com.example.concert.dto.response.EventResponseDTO;
import com.example.concert.dto.response.RegistrationResponseDTO;
import com.example.concert.entities.Registration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RegistrationMapper extends BaseMapper<Registration, RegistrationResponseDTO> {
    @Override
    default RegistrationResponseDTO toDto(Registration entity){
        return RegistrationResponseDTO.builder()
                .registrationId(entity.getRegistrationId())
                .userId(entity.getUser().getUserId())
                .eventId(entity.getEvent().getEventId())
                .seats(entity.getSeats())
                .build();
    }

}
