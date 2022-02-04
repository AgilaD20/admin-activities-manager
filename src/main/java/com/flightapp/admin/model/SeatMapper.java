package com.flightapp.admin.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SeatMapper {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="seatmapperid")
	private Integer seatId;
	
	@Column(name="flightid")
	private Integer flightId;
	
	@Column(name="seatnumber")
	private String seatNumber;
	
	@Column(name="departuredate")
	private LocalDate departureDate;
	

}
