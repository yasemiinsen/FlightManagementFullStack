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
public class AirlineSaveRequestDto {


    @NotBlank(message = "Airline code is required")
    @Pattern(regexp = "^[A-Z0-9]{2,3}$", message = "Airline code must be 2-3 uppercase letters or numbers")
    private String code;

    @NotBlank(message = "Airline name is required")
    @Size(min = 2, max = 100, message = "Airline name must be between 2 and 100 characters")
    private String name;


}