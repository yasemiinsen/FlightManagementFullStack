package com.Flight.service.concretes;

import com.Flight.core.exception.NotFoundException;
import com.Flight.core.utilies.Msg;
import com.Flight.entity.Airline;
import com.Flight.repository.AirlineRepository;
import com.Flight.service.abstracts.AirlineService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service

public class AirlineServiceImpl implements AirlineService {

    private final AirlineRepository airlineRepository;

    public AirlineServiceImpl(AirlineRepository airlineRepository) {
        this.airlineRepository = airlineRepository;
    }

    @Override
    public Airline save(Airline airline) {
        return this.airlineRepository.save(airline);
    }

    @Override
    public Airline get(int id) {
        return this.airlineRepository.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public List<Airline> getAll() {
        return this.airlineRepository.findAll();  // Fetch all airlines from repository
    }
    @Override
    public Airline update(Airline airline) {
        this.get(airline.getId());
        return this.airlineRepository.save(airline);
    }

    @Override
    public boolean delete(int id) {
        Airline airline = this.get(id);
        this.airlineRepository.delete(airline);
        return true;
    }



    /*@Override
    @Transactional(readOnly = true)
    public List<Airline> getAllAirlines() {
        log.debug("Retrieving all airlines");
        return airlineRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Airline getAirlineById(Integer id) {
        log.debug("Retrieving airline with id: {}", id);
        return airlineRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Airline", "id", id));
    }

    @Override
    public Airline createAirline(Airline airline) {
        log.debug("Creating new airline with code: {}", airline.getAirlineCode());
        if (existsByAirlineCode(airline.getAirlineCode())) {
            throw new DuplicateResourceException("Airline code already exists: " + airline.getAirlineCode());
        }
        return airlineRepository.save(airline);
    }

    @Override
    public Airline updateAirline(Airline airline) {
        log.debug("Updating airline with id: {}", airline.getAirlineId());
        if (existsByAirlineCodeAndIdNot(airline.getAirlineCode())) {
            throw new DuplicateResourceException("Airline code already exists: " + airline.getAirlineCode());
        }
        getAirlineById(airline.getAirlineId()); // Verify exists
        return airlineRepository.save(airline);
    }

    @Override
    public void deleteAirline(Integer id) {
        log.debug("Deleting airline with id: {}", id);
        getAirlineById(id); // Verify exists
        airlineRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Airline> findByAirlineCode(String airlineCode) {
        log.debug("Finding airline by code: {}", airlineCode);
        return airlineRepository.findByAirlineCode(airlineCode);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Airline> findByAirlineName(String airlineName) {
        log.debug("Finding airline by name: {}", airlineName);
        return airlineRepository.findByAirlineName(airlineName);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Airline> findByCountry(String country) {
        log.debug("Finding airlines by country: {}", country);
        return airlineRepository.findByCountryOfOrigin(country);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Airline> searchByNameContaining(String keyword) {
        log.debug("Searching airlines by name containing: {}", keyword);
        return airlineRepository.searchByNameContaining(keyword);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByAirlineCode(String airlineCode) {
        return airlineRepository.existsByAirlineCode(airlineCode);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByAirlineCodeAndIdNot(String code) {
        return airlineRepository.existsByAirlineCodeAndIdNot(code);
    }*/
}