package com.Flight.repository;

import com.Flight.entity.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Integer> {
    
/*    Optional<Airport> findByAirportCode(String airportCode);
    
    Optional<Airport> findByAirportName(String airportName);
    
    List<Airport> findByCity(String city);
    
    List<Airport> findByCountry(String country);
    List<Airport> findByCityAndCountry( String city,  String country);*/
    boolean existsByName(String airportName);
    
    @Query("SELECT a FROM Airport a WHERE LOWER(a.name) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Airport> searchByNameContaining(@Param("keyword") String keyword);

    

}