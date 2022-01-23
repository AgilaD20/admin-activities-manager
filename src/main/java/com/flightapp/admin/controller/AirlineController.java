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
import org.springframework.web.bind.annotation.PostMapping;
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

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1.0/flight/airline")
@Slf4j
public class AirlineController {
	
	private final AirlineService airlineService;
	
	private final FlightService flightService;
	
	private final ModelMapper modelMapper;
	

	
	@Autowired
	public AirlineController(AirlineService airlineService,FlightService flightService, ModelMapper modelMapper) {
		this.airlineService = airlineService;
		this.flightService = flightService;
		this.modelMapper = modelMapper;
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Airline>> getAllAirlines()
	{
		return ResponseEntity.status(HttpStatus.OK).body(airlineService.getAllAirlines());
	}
	
	@PostMapping("/register")
	public ResponseEntity<AirlineDTO> registerAirline(@Validated @RequestBody Airline airline)
	{
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		AirlineDTO airlineDTO = modelMapper.map(airlineService.registerAirline(airline),AirlineDTO.class);
		return ResponseEntity.status(HttpStatus.CREATED).body(airlineDTO);
	}
	
	
	//To be implemented to add flights
	@PostMapping("/inventory/add")
	public ResponseEntity<FlightDTO> addInventoryToAirline(@RequestBody CreateFlightDTO createRequest)
	{ 
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		FlightDTO flight = modelMapper.map(flightService.addFlight(createRequest),FlightDTO.class);
		return ResponseEntity.status(HttpStatus.OK).body(flight);
	}
	
	
	@DeleteMapping("/delete")
	public ResponseEntity<ApiResponse> deleteAirline(@Validated @RequestBody Airline airline)
	{
		airlineService.deleteAirline(airline);
		return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Airline was deleted succesfully"));
	}
}
