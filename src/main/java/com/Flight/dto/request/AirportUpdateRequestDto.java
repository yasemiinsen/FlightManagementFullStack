package com.Flight.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AirportUpdateRequestDto {
    


    private int id; // ID eklendi

    @NotBlank(message = "Airport name is required")
    @Size(min = 2, max = 100, message = "Airport name must be between 2 and 100 characters")
    private String name;

    @NotBlank(message = "City is required")
    @Size(min = 2, max = 100, message = "City name must be between 2 and 100 characters")
    private String city;

    @NotBlank(message = "Country is required")
    @Size(min = 2, max = 100, message = "Country name must be between 2 and 100 characters")
    private String country;
}