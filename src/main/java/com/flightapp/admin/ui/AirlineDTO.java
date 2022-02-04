package com.flightapp.admin.ui;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AirlineDTO {
	
	private Integer airlineId;
	
	private String airlineName;
	
	private String address;
	
	private String contact;
	
	private Boolean isBlocked;

}
