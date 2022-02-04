package com.flightapp.admin.repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.flightapp.admin.model.Flight;

@Repository
public interface FlightRepository extends JpaRepository<Flight,Integer> {
	
	
	@Query(value="select * from flight where fromlocation=:fromLocation and destination=:destination and departuredate=:departure and isblocked=0",nativeQuery=true)
	public List<Flight> getAllFlightByCriteria(String fromLocation, String destination, Date departure);
	
	@Modifying
	@Query(value="update flight set availableseats=:availableSeats where flightid=:flightId ",nativeQuery=true)
	public List<Flight> UpdateAvailableSeats(Integer availableSeats, Integer flightId);

	@Query
	public Optional<Flight> findByFlightName(String flightName);
	
	@Modifying
	@Query(value="update flight set isblocked=1 where airlineid=:airlineid", nativeQuery=true)
	public void blockFlightsByAirline(Integer airlineid);
	
	@Modifying
	@Query(value="update flight set isblocked=0 where airlineid=:airlineid", nativeQuery=true)
	public void unblockFlightsByAirline(Integer airlineid);
	
	@Query(value="select * from flight where airlineid=:airlineId", nativeQuery=true)
	public List<Flight> getAllFlightsByAirlineid(Integer airlineId);

	@Query(value="select * from flight where fromlocation=:fromLocation and destination=:destination and airlineid=:airlineId", nativeQuery=true)
	public List<Flight> getAllFlightsByCriteriaWithAirline(Integer airlineId, String fromLocation, String destination);

}
