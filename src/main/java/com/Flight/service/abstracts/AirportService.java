package com.Flight.service.abstracts;

import com.Flight.dto.request.AirportSaveRequestDto;
import com.Flight.dto.request.AirportUpdateRequestDto;
import com.Flight.dto.response.AirportResponseDto;
import com.Flight.entity.Airport;
import com.Flight.entity.Flight;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface AirportService {
    Airport save(Airport destinationAirport);

    Airport get(int id);

    Airport update(Airport destinationAirport);

    boolean delete(int id);


    List<Flight> getFlightsByAirport(int airportId);

    List<Airport> getAll();

    /*List<Airport> getAllAirports();


    Optional<Airport> getAirportById(Integer airportId);

    AirportResponseDto createAirport(AirportSaveRequestDto airportSaveRequestDto);


    AirportResponseDto updateAirport(AirportUpdateRequestDto airportUpdateRequestDto);


    void deleteAirport(AirportSaveRequestDto airportSaveRequestDto);


    Optional<Airport> findByAirportCode(String airportCode);


    List<Airport> findByCity(String city);

    List<Airport> findByCountry(String country);


    List<Airport> searchByNameContaining(String keyword);*/
}