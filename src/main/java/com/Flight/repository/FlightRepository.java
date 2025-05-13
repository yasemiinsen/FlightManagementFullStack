package com.Flight.repository;

import com.Flight.entity.Airport;
import com.Flight.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Integer> {
    // ekledim
    boolean existsByNumber(String number);

    @Query("SELECT COUNT(f) FROM Flight f " +
            "WHERE f.sourceAirport.id = :sourceId " +
            "AND f.destinationAirport.id = :destId " +
            "AND f.dateTime BETWEEN :startDate AND :endDate")
    int countDailyFlights(@Param("sourceId") int sourceId,
                          @Param("destId") int destId,
                          @Param("startDate") LocalDateTime startDate,
                          @Param("endDate") LocalDateTime endDate);

    // repository klasöründe
    List<Flight> findBySourceAirportIdOrDestinationAirportId(int sourceId, int destinationId);

    List<Flight> findByDateTimeBetween(LocalDateTime start, LocalDateTime end);

    long countBySourceAirportIdAndDestinationAirportId(int sourceAirportId, int destinationAirportId);



    /*@Query("SELECT COUNT(f) FROM Flight f " +
           "WHERE f.airline.id = :airlineId " +
           "AND f.departureAirport.airportId = :departureId " +
           "AND f.arrivalAirport.airportId = :arrivalId " +
           "AND DATE(f.departureTime) = :date")
    int countDailyFlightsByAirlineAndRoute(
            @Param("airlineId") Integer airlineId,
            @Param("departureId") Integer departureId,
            @Param("arrivalId") Integer arrivalId,
            @Param("date") LocalDate date);

    @Query("SELECT f FROM Flight f " +
           "WHERE f.airline.airlineName = :airlineName " +
           "AND f.departureAirport.airportName = :departureName " +
           "AND f.arrivalAirport.airportName= :arrivalName " +
           "AND f.departureTime BETWEEN :startTime AND :endTime")
    List<Flight> findFlightsByAirlineAndRoute(
            @Param("airlineName") Integer airlineName,
            @Param("departureName") Integer departureName,
            @Param("arrivalName") Integer arrivalName,
            @Param("startTime") LocalDateTime startTime,
            @Param("endTime") LocalDateTime endTime);

    @Query("SELECT f FROM Flight f " +
           "WHERE f.departureAirport.city = :departureCity " +
           "AND f.arrivalAirport.city = :arrivalCity " +
           "AND DATE(f.departureTime) = :date")
    List<Flight> findFlightsByCities(
            @Param("departureCity") String departureCity,
            @Param("arrivalCity") String arrivalCity,
            @Param("date") LocalDate date);

    @Query("SELECT f FROM Flight f " +
           "WHERE f.airline.airlineCode = :airlineCode " +
           "AND DATE(f.departureTime) = :date")
    List<Flight> findFlightsByAirlineAndDate(
            @Param("airlineCode") String airlineCode,
            @Param("date") LocalDate date);

    @Query("SELECT f FROM Flight f " +
           "WHERE f.departureTime BETWEEN :startTime AND :endTime " +
           "ORDER BY f.departureTime")
    List<Flight> findFlightsByTimeRange(
            @Param("startTime") LocalDateTime startTime,
            @Param("endTime") LocalDateTime endTime);*/
}