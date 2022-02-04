package com.flightapp.admin.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.flightapp.admin.model.Airline;
import com.flightapp.admin.service.AirlineService;
import com.flightapp.admin.service.FlightService;
import com.flightapp.admin.ui.AirlineDTO;
import com.flightapp.admin.ui.ApiResponse;
import com.flightapp.admin.ui.CreateFlightDTO;
import com.flightapp.admin.ui.FlightDTO;
import com.flightapp.admin.ui.FlightSearchDTO;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1.0/flight/airline")
@Slf4j
public class AirlineController {

	private final AirlineService airlineService;

	private final FlightService flightService;

	private final ModelMapper modelMapper;

	@Autowired
	public AirlineController(AirlineService airlineService, FlightService flightService, ModelMapper modelMapper) {
		this.airlineService = airlineService;
		this.flightService = flightService;
		this.modelMapper = modelMapper;
	}

	@GetMapping("/allairlines")
	public ResponseEntity<List<AirlineDTO>> getAllAirlines() {
		return ResponseEntity.status(HttpStatus.OK).body(airlineService.getAllAirlines());
	}

	@PostMapping("/register")
	public ResponseEntity<AirlineDTO> registerAirline(@Validated @RequestBody Airline airline) {
		log.info("Received request to register a new airline");
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		AirlineDTO airlineDTO = modelMapper.map(airlineService.registerAirline(airline), AirlineDTO.class);
		log.info("Airline {} was registered successfully",airlineDTO.getAirlineName());
		return ResponseEntity.status(HttpStatus.CREATED).body(airlineDTO);
	}

	
	@PostMapping("/inventory/add")
	public ResponseEntity<FlightDTO> addInventoryToAirline(@RequestBody CreateFlightDTO createRequest) {
		log.info("Received request to add a flight to airline {}", createRequest.getAirlineName());
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		FlightDTO flight = modelMapper.map(flightService.addFlight(createRequest), FlightDTO.class);
		log.info("Flight {} was added successfully to the airline {}",flight.getFlightName(), createRequest.getAirlineName());
		return ResponseEntity.status(HttpStatus.OK).body(flight);
	}

	@DeleteMapping("/delete")
	public ResponseEntity<ApiResponse> deleteAirline(@Validated @RequestBody Airline airline) {
		log.info("Received request to delete airline");
		airlineService.deleteAirline(airline);
		log.info("Delete airline successfully");
		return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Airline was deleted succesfully"));
	}

	@PutMapping("/blockairline/{airlineName:[a-zA-Z &+-]*}")
	public ResponseEntity<ApiResponse> blockAirline(@Validated @PathVariable String airlineName) {
		airlineService.blockAirline(airlineName);
		return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Airline was blocked"));
	}

	@PutMapping("/unblockairline/{airlineName:[a-zA-Z &+-]*}")
	public ResponseEntity<ApiResponse> UnblockAirline(@Validated @PathVariable String airlineName) {
		airlineService.unblockAirline(airlineName);
		return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Airline was unblocked"));
	}

	@GetMapping("/getFlights/{airlineName:[a-zA-Z &+-]*}")
	public ResponseEntity<List<FlightDTO>> getAllFlightByAirlineName(@PathVariable String airlineName) {
		return ResponseEntity.status(HttpStatus.OK).body(flightService.getAllFlightByAirlineName(airlineName));
	}

	@PostMapping("/searchschedule")
	public ResponseEntity<List<FlightDTO>> getFlightsByCriteria(@RequestBody FlightSearchDTO flightSearchdto) {
		return ResponseEntity.status(HttpStatus.OK).body(flightService.getFlightsByCriteria(flightSearchdto));
	}

	/*
	 * @PutMapping("/updateschedule") public ResponseEntity<FlightDTO>
	 * updateFlightSchedule(@RequestBody FlightDTO flightDTO) {
	 * 
	 * }
	 */

}
