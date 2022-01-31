package com.flightapp.admin.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.flightapp.admin.model.Airline;

@Repository
public interface AirlineRepository extends JpaRepository<Airline,Integer> {
	

	@Modifying
	@Query(value="delete from airline where airlinename=:airlinename",nativeQuery=true)
	void deleteByairlineId(String airlinename);

	@Query
	Optional<Airline> findByAirlineName(String airlineName);

	@Modifying
	@Query(value="update airline set isblocked =1 where airlineName=:airlineName", nativeQuery=true)
	void blockAirline(String airlineName);
	
	@Modifying
	@Query(value="update airline set isblocked =0 where airlineName=:airlineName", nativeQuery=true)
	void unblockAirline(String airlineName);

}
