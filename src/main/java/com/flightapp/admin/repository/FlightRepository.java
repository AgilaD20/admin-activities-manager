package com.flightapp.admin.repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.flightapp.admin.model.Airline;
import com.flightapp.admin.model.Flight;

@Repository
public interface FlightRepository extends JpaRepository<Flight,Integer> {
	
	
	@Query(value="select * from flight where fromlocation=:fromLocation and destination=:destination and departuretime=:departure and arrivaltime=:arrival ",nativeQuery=true)
	public List<Flight> getAllFlightByCriteria(String fromLocation, String destination, Date departure, Date arrival);
	
	@Modifying
	@Query(value="udpate flight set availableseats=:availableSeats where flightid=:flightId ",nativeQuery=true)
	public List<Flight> UpdateAvailableSeats(Integer availableSeats, Integer flightId);

	@Query
	public Optional<Flight> findByFlightName(String flightName);
	
	

}
