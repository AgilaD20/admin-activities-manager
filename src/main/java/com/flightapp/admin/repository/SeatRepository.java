package com.flightapp.admin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.flightapp.admin.model.Seat;

@Repository
public interface SeatRepository extends JpaRepository<Seat,Integer> {
	
	@Query(value="update seat set isbooked=true where flightid=:flightId and seatnumber=:SeatNumber",nativeQuery=true)
	public void updateBookedSeats(Integer flightId, String SeatNumber);
	
	@Query(value="select * from seat where isbooked=false and flightId=:flightId",nativeQuery=true)
	public List<Seat> getAvailableSeats(Integer flightId);

}
