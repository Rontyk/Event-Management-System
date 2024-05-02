package com.example.concert.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
public class RegistrationResponseDTO {
    @JsonProperty("registration_id")
    private Long registrationId;

    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("event_id")
    private Long eventId;

    @JsonProperty("seats")
    private Integer seats;
}
