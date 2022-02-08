package com.flightapp.admin.ui;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FlightUpdateDTO {
	
	private String flightName;
	
	private LocalDate endDate;

}
