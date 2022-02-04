package com.flightapp.admin.ui;

import java.time.LocalDate;
import java.util.List;

import com.flightapp.admin.model.TripType;
import com.flightapp.admin.model.scheduleDays;

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
	
	private LocalDate departureDate;
	
	private LocalDate endDate;
	
	private String airlineName;
	
	private List<scheduleDays> requestedSchedule;


}
