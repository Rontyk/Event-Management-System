package com.example.concert.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
@Data
public class EventRequestDTO {
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

