//package com.Flight;
//
//import com.Flight.entity.Flight;
//import com.Flight.repository.FlightRepository;
//import com.Flight.service.abstracts.FlightService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.time.LocalDateTime;
//import java.util.Collections;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//class FlightServiceTest {
//
//    @Mock
//    private FlightRepository flightRepository;
//
//    @InjectMocks
//    private FlightService flightService;
//
//    private Flight flight;
//
//    @BeforeEach
//    void setUp() {
//        // MockitoAnnotations.openMocks(this); // @ExtendWith(MockitoExtension.class) kullandığımız için gerek yok
//        flight = new Flight();
//        flight.setFlightId(1);
//        flight.setAirlineCode("THY");
//        flight.setFlightNumber("TK1234");
//        flight.setSource("ANK");
//        flight.setDestination("LON");
//        flight.setDepartureTime(LocalDateTime.now().plusDays(1));
//    }
//
//    @Test
//    void testCreateFlight_Success() {
//        // Hazırlık
//        LocalDateTime departureTime = LocalDateTime.now().plusDays(1);
//        LocalDateTime startTime = departureTime.minusMinutes(1);
//        LocalDateTime endTime = departureTime.plusMinutes(1);
//
//        when(flightRepository.findByAirlineCodeAndSourceAndDestinationAndDepartureTimeBetween(
//                flight.getAirlineCode(), flight.getSource(), flight.getDestination(), startTime, endTime
//        )).thenReturn(Collections.emptyList());
//
//        when(flightRepository.save(any(Flight.class))).thenReturn(flight);
//
//        // Act
//        Flight savedFlight = flightService.createFlight(flight);
//
//        // Doğrulama
//        assertNotNull(savedFlight);
//        assertEquals("THY", savedFlight.getAirlineCode());
//        assertEquals("TK1234", savedFlight.getFlightNumber());
//
//        verify(flightRepository, times(1)).save(any(Flight.class));
//    }
//
//    @Test
//    void testCreateFlight_FlightAlreadyExists() {
//        // Arrange
//        LocalDateTime departureTime = LocalDateTime.now().plusDays(1);
//        LocalDateTime startTime = departureTime.minusMinutes(1);
//        LocalDateTime endTime = departureTime.plusMinutes(1);
//
//        when(flightRepository.findByAirlineCodeAndSourceAndDestinationAndDepartureTimeBetween(
//                flight.getAirlineCode(), flight.getSource(), flight.getDestination(), startTime, endTime
//        )).thenReturn(Collections.singletonList(flight)); // Uçuşun zaten var olduğunu
//
//        // Act & Assert
//        assertThrows(IllegalArgumentException.class, () -> {
//            flightService.createFlight(flight);
//        });
//
//        verify(flightRepository, never()).save(any(Flight.class)); // Save metodu çağırılmadı?
//    }
//}
