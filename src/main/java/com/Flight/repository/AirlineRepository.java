package com.Flight.repository;

import com.Flight.entity.Airline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AirlineRepository extends JpaRepository<Airline, Integer> {
    
   /* Optional<Airline> findByAirlineCode(String airlineCode);
    
    Optional<Airline> findByAirlineName(String airlineName);
    
    List<Airline> findByCountryOfOrigin(String country);
    
    boolean existsByAirlineCode(String airlineCode);*/
    
    @Query("SELECT a FROM Airline a WHERE LOWER(a.name) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Airline> searchByNameContaining(@Param("keyword") String keyword);
    
    @Query("SELECT COUNT(a) > 0 FROM Airline a WHERE a.code = :code")
    boolean existsByAirlineCodeAndIdNot(@Param("code") String code);
}