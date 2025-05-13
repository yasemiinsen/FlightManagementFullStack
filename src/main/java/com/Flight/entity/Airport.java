package com.Flight.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "airports")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Airport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "airport_id")
    private int id;

    @Size(min = 2, max = 100, message = "Airport name must be between 2 and 100 characters")
    @Column(name = "airport_name")
    @NotNull
    private String name;

    @Size(min = 2, max = 100, message = "City name must be between 2 and 100 characters")
    @Column(name = "city")
    private String city;

    @Size(min = 2, max = 100, message = "Country name must be between 2 and 100 characters")
    @Column(name = "country")
    private String country;

   /* @OneToMany(mappedBy = "airport")
    private List<Flight> flights;
*/
    // ekledim
    @OneToMany(mappedBy = "sourceAirport")
    private List<Flight> departureFlights;

    @OneToMany(mappedBy = "destinationAirport")
    private List<Flight> arrivalFlights;


}