package com.Flight.dto.request;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FlightSaveRequestDto {

    private Integer id; // update için gerekli

    @Pattern(regexp = "^[A-Z0-9]{2,6}$", message = "Flight number must be 2-6 alphanumeric characters")
    private String number;

    @Future(message = "Flight time must be in the future")
    @NotNull(message = "Flight time is required")
    private LocalDateTime dateTime;

    @NotNull
    private Integer airlineId;

    @NotNull
    private Integer sourceAirportId;

    @NotNull
    private Integer destinationAirportId;
}
