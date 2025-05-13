package com.Flight.service.abstracts;

import com.Flight.entity.Airline;
import java.util.List;
import java.util.Optional;

public interface AirlineService {


    Airline save(Airline airline);

    Airline get(int id);

    Airline update(Airline airline);

    boolean delete(int id);

    List<Airline> getAll();







  /*  *//**
     * Retrieve all airlines
     *//*
    List<Airline> getAllAirlines();

    *//**
     * Get airline by ID
     * @throws ResourceNotFoundException if airline not found
     *//*
    Airline getAirlineById(Integer id);

    *//**
     * Create a new airline
     * @throws DuplicateResourceException if airline code already exists
     *//*
    Airline createAirline(Airline airline);

    *//**
     * Update existing airline
     * @throws ResourceNotFoundException if airline not found
     * @throws DuplicateResourceException if airline code already exists for different airline
     *//*
    Airline updateAirline(Airline airline);

    *//**
     * Delete airline by ID
     * @throws ResourceNotFoundException if airline not found
     *//*
    void deleteAirline(Integer id);

    *//**
     * Find airline by code
     *//*
    Optional<Airline> findByAirlineCode(String airlineCode);

    *//**
     * Find airline by name
     *//*
    Optional<Airline> findByAirlineName(String airlineName);

    *//**
     * Find airlines by country
     *//*
    List<Airline> findByCountry(String country);

    *//**
     * Search airlines by name containing keyword
     *//*
    List<Airline> searchByNameContaining(String keyword);

    *//**
     * Check if airline code exists
     *//*
    boolean existsByAirlineCode(String airlineCode);

    *//**
     * Check if airline code exists for different airline
     *//*
    boolean existsByAirlineCodeAndIdNot(String code);*/
}