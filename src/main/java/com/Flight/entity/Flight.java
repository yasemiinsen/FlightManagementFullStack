package com.Flight.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "flights")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Flight {

    @Id
/*
    @GeneratedValue(strategy = GenerationType.IDENTITY)
*/
    @Column(name = "flight_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Pattern(regexp = "^[A-Z0-9]{2,6}$", message = "Flight number must be 2-6 alphanumeric characters")
    @Column(name = "flight_number", nullable = false)
    private String number;

    @Future(message = "flight time must be in the future")
    @Column(name = "flight_date_time", nullable = false)
    @NotNull(message = "Flight time is required")
    private LocalDateTime dateTime;

    /*@ManyToOne()
    @JoinColumn(name = "flight_airport_id",referencedColumnName = "airport_id")
    private Airport airport;*/

    @ManyToOne()
    @JoinColumn(name = "flight_airline_id", referencedColumnName = "airline_id")
    private Airline airline;

    //ekledim
    @ManyToOne
    @JoinColumn(name = "source_airport_id", referencedColumnName = "airport_id")
    private Airport sourceAirport;

    @ManyToOne
    @JoinColumn(name = "destination_airport_id", referencedColumnName = "airport_id")
    private Airport destinationAirport;



}