package com.jfsfeb.airlinereservationsystemwithhibernate.dao;

import java.util.List;

import com.jfsfeb.airlinereservationsystemwithhibernate.dto.FlightDetails;
import com.jfsfeb.airlinereservationsystemwithhibernate.dto.TicketsInfo;
import com.jfsfeb.airlinereservationsystemwithhibernate.dto.UserDetails;

public interface AdminDAO {

	public boolean addFlights(FlightDetails flights);

	public boolean addNewAdmin(UserDetails admin);

	public boolean deleteFlights(int id);

	public List<FlightDetails> searchFlight(int id);

	public List<FlightDetails> viewFlightDetails();

	public List<UserDetails> viewAllUserDetails();

	public List<TicketsInfo> viewTicketsInfos();

}
