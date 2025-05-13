package com.Flight.api;

import com.Flight.core.config.modelMapper.IModelMapperService;
import com.Flight.core.result.Result;
import com.Flight.core.result.ResultData;
import com.Flight.core.utilies.ResultHelper;
import com.Flight.dto.response.AirlineResponseDto;
import com.Flight.dto.request.AirlineSaveRequestDto;
import com.Flight.dto.request.AirlineUpdateRequestDto;
import com.Flight.entity.Airline;
import com.Flight.service.abstracts.AirlineService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/airlines")
public class AirlineController {
    private final AirlineService airlineService;
    private final IModelMapperService modelMapper;

    public AirlineController(AirlineService airlineService, IModelMapperService modelMapper) {
        this.airlineService = airlineService;
        this.modelMapper = modelMapper;
    }
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<AirlineResponseDto> save(@Valid @RequestBody AirlineSaveRequestDto airlineSaveRequestDto) {
        Airline saveAirline = this.modelMapper.forRequest().map(airlineSaveRequestDto, Airline.class);
        this.airlineService.save(saveAirline);
        return ResultHelper.created(this.modelMapper.forResponse().map(saveAirline,AirlineResponseDto.class));

    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AirlineResponseDto> update(@Valid @RequestBody AirlineUpdateRequestDto airlineUpdateRequestDto) {
        Airline updateAirline = this.modelMapper.forRequest().map(airlineUpdateRequestDto, Airline.class);
        this.airlineService.update(updateAirline);
        return ResultHelper.success(this.modelMapper.forResponse().map(updateAirline,AirlineResponseDto.class));
    }

    // Endpoint to get all airlines
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<AirlineResponseDto>> getAll() {
        List<Airline> airlines = airlineService.getAll();  // Get all airlines from the service
        List<AirlineResponseDto> airlineDtos = airlines.stream()
                .map(airline -> modelMapper.forResponse().map(airline, AirlineResponseDto.class))
                .collect(Collectors.toList());  // Convert to response DTO
        return ResultHelper.success(airlineDtos);  // Return the list wrapped in a ResultData
    }

    //taih aralığı

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") int id){
        this.airlineService.delete(id);
        return ResultHelper.ok();
    }





    /*@GetMapping("/airlines")
    public List<AirlineResponseDto> getAllAirlines() {
        log.info("Fetching all airlines");
        return airlineService.getAllAirlines()
                .stream()
                .map(this::convertToDto)  // Fixed: Remove parentheses and parameter
                .collect(Collectors.toList());
    }

    @GetMapping("/airlines/{airlineCode}")
    public List<AirlineResponseDto> getAirlineByAirlineCode(@PathVariable String airlineCode) {
        log.info("Fetching airline with code: {}", airlineCode);
        return airlineService.getAirlineByAirlineCode(airlineCode)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/airlines/{airlineCode}")
    public ResponseEntity<AirlineResponseDto> createAirline(@RequestBody AirlineResponseDto airlineResponseDto) {
        Airline airline = convertToEntity(airlineResponseDto);
        Airline createdAirline = airlineService.createAirline(airline);
        AirlineResponseDto createdAirlineResponseDto = convertToDto(createdAirline);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAirlineResponseDto);
    }

    @PutMapping("/airlines/{airlineCode}")
    public ResponseEntity<AirlineResponseDto> updateAirline(
            @Valid @RequestBody AirlineResponseDto airlineResponseDto) {
        log.info("Updating airline with id: {}", airlineAirlineCode);
        return airlineService.updateAirline(airlineCode);
        convertToEntity(airlineResponseDto);

        // Verify airline exists
        airlineService.getAirlineByAirlineCode(airlineCode);


    }

    @DeleteMapping("/airlines/{airlineCode}")

    public ResponseEntity<Void> deleteAirline(@PathVariable Integer airlineCode) {
        airportService.deleteAirline(airlineId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }



    private AirlineResponseDto convertToDto(Airline airline) {
        AirlineResponseDto airlineResponseDto = new AirlineResponseDto();
//        airlineResponseDto.setAirlineAirlineCode(airline.getAirlineAirlineCode());
        return airlineResponseDto;
    }

    private Airline convertToEntity(AirlineResponseDto airlineResponseDto) {
        Airline airline = new Airline();
        return airline;
    }*/
}