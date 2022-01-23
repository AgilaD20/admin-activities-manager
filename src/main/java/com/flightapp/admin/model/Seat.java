package com.flightapp.admin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Seat {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="seatid")
	private Integer seatId;
	
	@ManyToOne
	@JoinColumn(name="flightid")
	private Flight flight;
	
	@Column(name="seatnumber")
	private String seatNumber;
	
	@Column(name="isbooked")
	private Boolean isBooked;
	
	public Seat(Flight flight, String seatNumber, Boolean isBooked)
	{
		this.flight = flight;
		this.seatNumber = seatNumber;
		this.isBooked = isBooked;
	}

}
