package com.flightapp.admin.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flightapp.admin.exception.EntityAlreadyPresentException;
import com.flightapp.admin.exception.EntityNotPresentException;
import com.flightapp.admin.model.Airline;
import com.flightapp.admin.model.Flight;
import com.flightapp.admin.model.Schedules;
import com.flightapp.admin.model.Seat;
import com.flightapp.admin.model.scheduleDays;
import com.flightapp.admin.repository.AirlineRepository;
import com.flightapp.admin.repository.FlightRepository;
import com.flightapp.admin.repository.ScheduleRepository;
import com.flightapp.admin.repository.SeatRepository;
import com.flightapp.admin.ui.CreateFlightDTO;
import com.flightapp.admin.ui.FlightDTO;
import com.flightapp.admin.ui.FlightSearchDTO;

@Service
public class FlightService {

	private final AirlineRepository airlineRepository;
	private final FlightRepository flightRepository;
	private final SeatRepository seatRepository;
	private final ModelMapper modelMapper;
	private final ScheduleRepository scheduleRepository;

	@Autowired
	public FlightService(AirlineRepository airlineRepository, FlightRepository flightRepository,
			SeatRepository seatRepository, ModelMapper modelMapper, ScheduleRepository scheduleRepository) {
		this.flightRepository = flightRepository;
		this.seatRepository = seatRepository;
		this.modelMapper = modelMapper;
		this.airlineRepository = airlineRepository;
		this.scheduleRepository = scheduleRepository;
	}

	@Transactional
	public Flight addFlight(CreateFlightDTO request) {
		if (flightRepository.findByFlightName(request.getFlightName()).isPresent())
			throw new EntityAlreadyPresentException("Flight is already present");
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		Flight flight = modelMapper.map(request, Flight.class);
		Optional<Airline> optionalAirline = airlineRepository.findByAirlineName(request.getAirlineName());
		if (optionalAirline.isEmpty()) {
			throw new EntityNotPresentException("Airline is not present to add the flight");
		}
		flight.setAirline(optionalAirline.get());
		flight.setIsBlocked(false);
		Flight addedFlight = flightRepository.save(flight);
		seatsGenerator(addedFlight, addedFlight.getAvailableSeats());
		scheduleGenerator(addedFlight, request.getRequestedSchedule());
		return addedFlight;
	}

	private void scheduleGenerator(Flight flight, List<scheduleDays> requestedSchedule) {

		requestedSchedule.stream().forEach(t -> {
			Schedules sch = new Schedules();
			sch.setFlight(flight);
			sch.setScheduledDays(t);
			scheduleRepository.save(sch);

		});

	}

	public void seatsGenerator(Flight flight, Integer availableSeats) {
		IntStream.range(1, availableSeats + 1).forEach(n -> seatRepository.save(new Seat(flight, "s" + n, false)));
	}

	public List<FlightDTO> getAllFlightByAirlineName(String airlineName) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		Optional<Airline> airline = airlineRepository.findByAirlineName(airlineName);
		List<Flight> flightList = flightRepository.getAllFlightsByAirlineid(airline.get().getAirlineId());
		List<FlightDTO> flightDTOlist = flightList.stream().map(flight -> modelMapper.map(flight, FlightDTO.class))
				.collect(Collectors.toList());
		return flightDTOlist;
	}

	public List<FlightDTO> getFlightsByCriteria(FlightSearchDTO flightSearchdto) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		Optional<Airline> airline = airlineRepository.findByAirlineName(flightSearchdto.getAirlineName());
		List<Flight> flightList = flightRepository.getAllFlightsByCriteriaWithAirline(airline.get().getAirlineId(),
				flightSearchdto.getFromLocation(), flightSearchdto.getDestination());
		List<FlightDTO> flightDTOlist = flightList.stream().map(flight -> modelMapper.map(flight, FlightDTO.class))
				.collect(Collectors.toList());
		return flightDTOlist;
	}

}
