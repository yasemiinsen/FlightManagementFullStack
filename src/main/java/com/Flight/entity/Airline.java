package com.Flight.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Table(name = "airlines")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Airline {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "airline_id")
    private int id;

    @Pattern(regexp = "^[A-Z0-9]{2,3}$", message = "Airline code must be 2-3 uppercase letters or numbers")
    @Column(name = "airline_code", length = 3)
    private String code;

    @Size(min = 2, max = 100, message = "Airline name must be between 2 and 100 characters")
    @Column(name = "airline_name", length = 100)
    private String name;


    @OneToMany(mappedBy = "airline")
    private List<Flight> flights;


    }
