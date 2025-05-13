package com.Flight.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FlightResponseDto {

    private Integer id;
    private String number;
    private LocalDateTime dateTime;
    private String airlineName;
    private String sourceAirportName;
    private String destinationAirportName;
}
