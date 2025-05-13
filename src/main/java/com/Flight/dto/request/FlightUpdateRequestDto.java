package com.Flight.dto.request;

import com.Flight.entity.Airline;
import com.Flight.entity.Airport;
import com.Flight.entity.Flight;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FlightUpdateRequestDto {



    @NotNull(message = "Flight number is required")
    @Pattern(regexp = "^[A-Z0-9]{2,6}$", message = "Flight number must be 2-6 alphanumeric characters")
    private String number;

    @NotNull(message = "Airline ID is required")
    @Min(value = 1, message = "Airline ID must be positive")
    private Integer id;


   /* private Airport airport;*/
    private Airline airline;


    @NotNull(message = "Flight time is required")
    @Future(message = "Flight time must be in the future")
    private LocalDateTime dateTime;


    // EKLEDİM
    private Flight destinationAirport;
    private Flight sourceAirport;

}