package com.Flight.service.concretes;

import com.Flight.core.exception.NotFoundException;
import com.Flight.core.utilies.Msg;
import com.Flight.entity.Flight;
import com.Flight.repository.FlightRepository;
import com.Flight.service.abstracts.FlightService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;

    public FlightServiceImpl(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @Override
    public Flight save(Flight flight) {
        long existingCount = flightRepository.countBySourceAirportIdAndDestinationAirportId(
                flight.getSourceAirport().getId(),
                flight.getDestinationAirport().getId()
        );

        if (existingCount >= 3) {
            return null;
        }

        return flightRepository.save(flight);
    }



    @Override
    public Flight get(int id) {
        return flightRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public Flight update(Flight flight) {
        get(flight.getId()); // check exists
        return flightRepository.save(flight);
    }

    @Override
    public boolean delete(int id) {
        Flight flight = get(id);
        flightRepository.delete(flight);
        return true;
    }

    @Override
    public List<Flight> getAll() {
        return flightRepository.findAll();
    }

    @Override
    public List<Flight> getDailyFlights() {
        LocalDate today = LocalDate.now();
        return flightRepository.findByDateTimeBetween(
                today.atStartOfDay(),
                today.plusDays(3).atStartOfDay()
        );
    }

    @Override
    public List<Flight> getFlightsByDateRange(LocalDateTime start, LocalDateTime end) {
        return flightRepository.findByDateTimeBetween(start, end);
    }
    @Override
    public List<Flight> getFlightsByAirport(int airportId) {
        return flightRepository.findBySourceAirportIdOrDestinationAirportId(airportId, airportId);
    }

}
