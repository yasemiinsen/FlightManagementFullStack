package com.Flight.service.concretes;

import com.Flight.core.exception.NotFoundException;
import com.Flight.core.utilies.Msg;
import com.Flight.entity.Airport;
import com.Flight.entity.Flight;
import com.Flight.exception.DuplicateResourceException;
import com.Flight.repository.AirportRepository;
import com.Flight.service.abstracts.AirportService;
import com.Flight.service.abstracts.FlightService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirportServiceImpl implements AirportService {
    private final AirportRepository airportRepository;
    private final FlightService flightService; // ✅ EKLENDİ

    // ✅ FlightService constructor'a eklendi
    public AirportServiceImpl(AirportRepository airportRepository, FlightService flightService) {
        this.airportRepository = airportRepository;
        this.flightService = flightService;
    }

    @Override
    public Airport save(Airport airport) {
        if (airportRepository.existsByName(airport.getName())) {
            throw new DuplicateResourceException("Airport name already exists: " + airport.getName());
        }
        return this.airportRepository.save(airport);
    }

    @Override
    public Airport get(int id) {
        return this.airportRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public List<Airport> getAll() {
        return this.airportRepository.findAll();
    }

    @Override
    public Airport update(Airport airport) {
        this.get(airport.getId());
        return this.airportRepository.save(airport);
    }

    @Override
    public boolean delete(int id) {
        Airport airport = this.get(id);
        this.airportRepository.delete(airport);
        return true;
    }

    // ✅ İŞTE EKLENMESİ GEREKEN METOD:
    @Override
    public List<Flight> getFlightsByAirport(int airportId) {
        return flightService.getFlightsByAirport(airportId);
    }
}
