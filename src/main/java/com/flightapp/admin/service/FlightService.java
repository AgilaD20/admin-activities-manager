package com.flightapp.admin.service;

import java.util.Optional;
import java.util.stream.IntStream;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flightapp.admin.exception.EntityAlreadyPresentException;
import com.flightapp.admin.exception.EntityNotPresentException;
import com.flightapp.admin.model.Airline;
import com.flightapp.admin.model.Flight;
import com.flightapp.admin.model.Seat;
import com.flightapp.admin.repository.AirlineRepository;
import com.flightapp.admin.repository.FlightRepository;
import com.flightapp.admin.repository.SeatRepository;
import com.flightapp.admin.ui.CreateFlightDTO;

@Service
public class FlightService {
	
	private final AirlineRepository airlineRepository;
	private final FlightRepository flightRepository;
	private final SeatRepository seatRepository;
	private final ModelMapper modelMapper;

	@Autowired
	public FlightService(AirlineRepository airlineRepository,FlightRepository flightRepository, SeatRepository seatRepository, ModelMapper modelMapper) {
		this.flightRepository = flightRepository;
		this.seatRepository = seatRepository;
		this.modelMapper = modelMapper;
		this.airlineRepository=airlineRepository;
	}
	
	


public Flight addFlight(CreateFlightDTO request){
	if(flightRepository.findByFlightName(request.getFlightName()).isPresent())
		throw new EntityAlreadyPresentException("Flight is already present");
	modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	Flight flight = modelMapper.map(request, Flight.class);
	Optional<Airline> optionalAirline = airlineRepository.findByAirlineName(request.getAirlineName());
	if(optionalAirline.isEmpty())
	{
		throw new EntityNotPresentException("Airline is not present to add the flight");
	}
	flight.setAirline(optionalAirline.get());
	flight.setIsBlocked(false);
	Flight addedFlight =flightRepository.save(flight);	
	seatsGenerator(addedFlight, addedFlight.getAvailableSeats());
	return addedFlight;
}

public void seatsGenerator(Flight flight, Integer availableSeats)
{
	IntStream.range(1, availableSeats).forEach(n->seatRepository.save(new Seat(flight,"s"+n,false)));
}



	
	

}