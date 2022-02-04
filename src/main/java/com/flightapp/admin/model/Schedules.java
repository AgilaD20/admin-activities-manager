package com.flightapp.admin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="schedules")
public class Schedules {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="scheduleid")
	private Integer scheduleid;
	
	@ManyToOne
	@JoinColumn(name="flightid")
	private Flight flight;
	
	@Column(name="scheduledays")
	private scheduleDays scheduledDays;

}
