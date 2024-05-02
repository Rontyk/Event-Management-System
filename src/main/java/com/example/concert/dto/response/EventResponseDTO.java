package com.example.concert.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
public class EventResponseDTO {
    @JsonProperty("event_id")
    private Long eventId;

    @JsonProperty("title")
    private String title;

    @JsonProperty("description")
    private String description;

    @JsonProperty("event_date")
    private LocalDateTime eventDate;

    @JsonProperty("location")
    private String location;

    @JsonProperty("available_seats")
    private Integer availableSeats;

}
