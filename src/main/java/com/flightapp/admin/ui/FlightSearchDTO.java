package com.flightapp.admin.ui;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FlightSearchDTO {
	
    private String airlineName;
	
	private String fromLocation;
	
	private String destination;

}
