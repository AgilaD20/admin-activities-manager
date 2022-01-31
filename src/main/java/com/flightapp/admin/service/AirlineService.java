package com.flightapp.admin.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flightapp.admin.exception.EntityAlreadyPresentException;
import com.flightapp.admin.exception.EntityNotPresentException;
import com.flightapp.admin.model.Airline;
import com.flightapp.admin.repository.AirlineRepository;
import com.flightapp.admin.repository.FlightRepository;
import com.flightapp.admin.ui.AirlineDTO;

@Service
public class AirlineService {
	
	private final AirlineRepository airlineRepository;
	
	private final FlightRepository flightRepository;
	
	private final ModelMapper modelMapper;
	
	@Autowired
	public AirlineService(AirlineRepository airlineRepository, FlightRepository flightRepository, ModelMapper modelMapper){
		this.airlineRepository = airlineRepository;
		this.flightRepository = flightRepository;
		this.modelMapper = modelMapper;
	}

	public List<AirlineDTO> getAllAirlines() throws EntityNotPresentException {
		
		List<Airline> airlineList = airlineRepository.findAll();
		if(airlineList.isEmpty()){
			throw new EntityNotPresentException("No airlines present");
		}
		List<AirlineDTO> airlineDTOlist = airlineList.stream().map(airline -> modelMapper.map(airline, AirlineDTO.class)).collect(Collectors.toList());
		return airlineDTOlist;
	}
	
	public Airline registerAirline(Airline airline)
	{
	if(airlineRepository.findByAirlineName(airline.getAirlineName()).isPresent())
		throw new EntityAlreadyPresentException("Airline already present");
	airline.setIsBlocked(false);
	return airlineRepository.save(airline);
	
	}
	
	public Airline getAirlinebyId(Integer airlineId)
	{
	
		Optional<Airline> airline = airlineRepository.findById(airlineId);
		if(!airline.isPresent()) {
			throw new EntityNotPresentException("No airline is present with the Id"+airlineId);
		}
		return airline.get();
		
	}
	
	public Airline UpdatedAirline(Airline airline){
		return null;	
	}
	
	@Transactional
	public void deleteAirline(Airline airline) {
		String name = airline.getAirlineName();
		airlineRepository.deleteByairlineId(name);
	}

	@Transactional
	public void blockAirline(String airlineName) {		
		airlineRepository.blockAirline(airlineName);
		Optional<Airline> airline = airlineRepository.findByAirlineName(airlineName);
		flightRepository.blockFlightsByAirline(airline.get().getAirlineId());
	}
	
	@Transactional
	public void unblockAirline(String airlineName) {		
		airlineRepository.unblockAirline(airlineName);
		Optional<Airline> airline = airlineRepository.findByAirlineName(airlineName);
		flightRepository.unblockFlightsByAirline(airline.get().getAirlineId());
	}

}
