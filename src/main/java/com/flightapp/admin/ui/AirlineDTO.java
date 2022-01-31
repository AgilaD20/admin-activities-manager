package com.flightapp.admin.ui;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.validation.constraints.Pattern;

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
