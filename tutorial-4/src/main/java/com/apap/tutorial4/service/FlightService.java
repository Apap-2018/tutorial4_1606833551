package com.apap.tutorial4.service;

import java.sql.Date;
import java.util.List;

import com.apap.tutorial4.model.FlightModel;

public interface FlightService {
	FlightModel getFlightDetailById(long id);
	
	void addFlight(FlightModel flight);

	void deleteFlightById(long id);

	void updateFlight(Long id, String flightNumber, String origin, String destination, Date time);
	
	List<FlightModel> allFlight();

}
