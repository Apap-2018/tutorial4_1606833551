package com.apap.tutorial4.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import com.apap.tutorial4.model.*;
import com.apap.tutorial4.service.*;

@Controller
public class FlightController {
	@Autowired
	private FlightService flightService;

	@Autowired
	private PilotService pilotService;

	@RequestMapping(value = "/flight/add/{licenseNumber}", method = RequestMethod.GET)
	private String add(@PathVariable(value = "licenseNumber") String licenseNumber, Model model) {
		FlightModel flight = new FlightModel();
		PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		flight.setPilot(pilot);
		model.addAttribute("flight", flight);
		return "addFlight";
	}

	@RequestMapping(value = "/flight/add", method = RequestMethod.POST)
	private String addFlightSubmit(@ModelAttribute FlightModel flight) {
		flightService.addFlight(flight);
		return "add";
	}

	@RequestMapping(value = "/flight/remove/{id}", method = RequestMethod.GET)
	private String deleteFlight(@PathVariable(value = "id") Long id, Model model) {
		flightService.deleteFlightById(id);
		return "removeFlight";
	}
	@RequestMapping(value="/flight/update/{licenseNumber}/{id}", method = RequestMethod.GET)
	private String updateFlight(@PathVariable(value = "id") Long id, @PathVariable("licenseNumber") String licenseNumber, Model model) {
		FlightModel flight = new FlightModel();
		PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		flight.setPilot(pilot);
		flight.setId(id);
		model.addAttribute("flight",flight);
		model.addAttribute("pilot",pilot);
		return "updateFlight";
	}
	
	@RequestMapping(value="/flight/update/{id}",method=RequestMethod.POST)
	private String updateFlightSubmit(@ModelAttribute FlightModel flight,@PathVariable(value= "id") Long id, Model model) {
		flightService.updateFlight(id, flight.getFlightNumber(), flight.getOrigin(), flight.getDestination(), flight.getTime());
		return "updateSuccess";
	}
	
	@RequestMapping(value ="/flight/view/{id}")
	private String viewFlight(@PathVariable(value = "id") long id, Model model) {
		FlightModel flight = flightService.getFlightDetailById(id);
		PilotModel pilotFlight = flight.getPilot();
		model.addAttribute("flight", flight);
		model.addAttribute("pilotFlight", pilotFlight);
		
		return "view-flight";
	}
	@RequestMapping(value ="/flight/view/")
	private String viewAll(Model model) {
		List<FlightModel> list = flightService.allFlight();
		model.addAttribute("list", list);
		return "viewAll";		
	}
	
	
}
