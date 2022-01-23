package com.flightapp.admin.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flightapp.admin.exception.EntityAlreadyPresentException;
import com.flightapp.admin.exception.EntityNotPresentException;
import com.flightapp.admin.model.Airline;
import com.flightapp.admin.repository.AirlineRepository;

@Service
public class AirlineService {
	
	private final AirlineRepository airlineRepository;
	
	@Autowired
	public AirlineService(AirlineRepository airlineRepository){
		this.airlineRepository = airlineRepository;
	}

	public List<Airline> getAllAirlines() {
		
		List<Airline> airlineList = airlineRepository.findAll();
		if(airlineList.isEmpty()){
			throw new EntityNotPresentException("No airlines present");
		}
		return airlineList;
	}
	
	public Airline registerAirline(Airline airline)
	{
	if(airlineRepository.findByAirlineName(airline.getAirlineName()).isPresent())
		throw new EntityAlreadyPresentException("Airline already present");
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
	
	public Airline UpdatedAirline(Airline airline)
	{
	
		return null;
		
	}
	@Transactional
	public void deleteAirline(Airline airline) {
		// TODO Auto-generated method stub
		String name = airline.getAirlineName();
		airlineRepository.deleteByairlineId(name);
	}

}
