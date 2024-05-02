package com.example.concert.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class RegistrationRequestDTO {
    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("event_id")
    private Long eventId;

    @JsonProperty("seats")
    private Integer seats;
}
