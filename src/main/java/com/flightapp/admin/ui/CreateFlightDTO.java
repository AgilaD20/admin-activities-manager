package com.flightapp.admin.ui;

import java.time.LocalDateTime;

import com.flightapp.admin.model.TripType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateFlightDTO {
	
private String flightName;
	
	private Integer availableSeats;
	
	private String fromLocation;
	
	private String destination;
	
	private Double price;
	
	private TripType tripType;
	
	private LocalDateTime departureTime;
	
	private LocalDateTime arrivalTime;
	
	private String airlineName;


}
