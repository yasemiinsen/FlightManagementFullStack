package com.Flight.service.abstracts;

import com.Flight.entity.Flight;

import java.time.LocalDateTime;
import java.util.List;

public interface FlightService {

    Flight save(Flight flight);

    Flight get(int id);

    Flight update(Flight flight);

    boolean delete(int id);

    List<Flight> getAll();

    List<Flight> getDailyFlights();

    List<Flight> getFlightsByDateRange(LocalDateTime start, LocalDateTime end);

    List<Flight> getFlightsByAirport(int airportId);


}
