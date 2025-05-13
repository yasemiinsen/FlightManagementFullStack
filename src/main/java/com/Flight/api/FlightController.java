package com.Flight.api;

import com.Flight.core.config.modelMapper.IModelMapperService;
import com.Flight.core.result.Result;
import com.Flight.core.result.ResultData;
import com.Flight.core.utilies.Msg;
import com.Flight.core.utilies.ResultHelper;
import com.Flight.dto.request.FlightSaveRequestDto;
import com.Flight.dto.response.FlightResponseDto;
import com.Flight.entity.Flight;
import com.Flight.service.abstracts.AirlineService;
import com.Flight.service.abstracts.AirportService;
import com.Flight.service.abstracts.FlightService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/flights")
public class FlightController {

    private final FlightService flightService;
    private final AirlineService airlineService;
    private final AirportService airportService;
    private final IModelMapperService modelMapper;

    public FlightController(FlightService flightService, AirlineService airlineService,
                            AirportService airportService, IModelMapperService modelMapper) {
        this.flightService = flightService;
        this.airlineService = airlineService;
        this.airportService = airportService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<FlightResponseDto> save(@Valid @RequestBody FlightSaveRequestDto dto) {
        Flight flight = modelMapper.forRequest().map(dto, Flight.class);
        flight.setAirline(airlineService.get(dto.getAirlineId()));
        flight.setSourceAirport(airportService.get(dto.getSourceAirportId()));
        flight.setDestinationAirport(airportService.get(dto.getDestinationAirportId()));

        try {
            flightService.save(flight); // Uçuşu kaydetmeye çalış
        } catch (RuntimeException e) {
            // Hata durumunda FLIGHT_LIMIT_EXCEEDED mesajını kullanıyoruz
            return ResultHelper.error(Msg.FLIGHT_LIMIT_EXCEEDED);
        }

        return ResultHelper.created(toDto(flight));
    }




    @GetMapping("/range")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<FlightResponseDto>> getFlightsByDateRange(
            @RequestParam String startDate,
            @RequestParam String endDate) {

        LocalDateTime start = LocalDate.parse(startDate).atStartOfDay();
        LocalDateTime end = LocalDate.parse(endDate).atTime(23, 59, 59);

        List<Flight> flights = flightService.getFlightsByDateRange(start, end);
        List<FlightResponseDto> dtos = flights.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
        return ResultHelper.success(dtos);
    }


    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ResultData<FlightResponseDto> update(@Valid @RequestBody FlightSaveRequestDto dto) {
        Flight flight = modelMapper.forRequest().map(dto, Flight.class);
        flight.setAirline(airlineService.get(dto.getAirlineId()));
        flight.setSourceAirport(airportService.get(dto.getSourceAirportId()));
        flight.setDestinationAirport(airportService.get(dto.getDestinationAirportId()));
        flightService.update(flight);
        return ResultHelper.success(toDto(flight));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<FlightResponseDto>> getAll() {
        List<FlightResponseDto> flights = flightService.getAll()
                .stream().map(this::toDto)
                .collect(Collectors.toList());
        return ResultHelper.success(flights);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable int id) {
        flightService.delete(id);
        return ResultHelper.ok();
    }

    // ✅ Günlük uçuşlar için ek endpoint
    @GetMapping("/daily")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<FlightResponseDto>> getDailyFlights() {
        List<Flight> flights = flightService.getDailyFlights();
        List<FlightResponseDto> dtos = flights.stream()
                .map(flight -> modelMapper.forResponse().map(flight, FlightResponseDto.class))
                .collect(Collectors.toList());
        return ResultHelper.success(dtos);
    }




    private FlightResponseDto toDto(Flight flight) {
        FlightResponseDto dto = modelMapper.forResponse().map(flight, FlightResponseDto.class);
        dto.setAirlineName(flight.getAirline().getName());
        dto.setSourceAirportName(flight.getSourceAirport().getName());
        dto.setDestinationAirportName(flight.getDestinationAirport().getName());
        return dto;
    }
}
