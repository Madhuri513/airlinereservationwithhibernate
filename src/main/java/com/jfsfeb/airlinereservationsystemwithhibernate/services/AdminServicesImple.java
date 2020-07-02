package com.jfsfeb.airlinereservationsystemwithhibernate.services;

import java.util.List;

import com.jfsfeb.airlinereservationsystemwithhibernate.dao.AdminDAO;
import com.jfsfeb.airlinereservationsystemwithhibernate.dto.FlightDetails;
import com.jfsfeb.airlinereservationsystemwithhibernate.dto.TicketsInfo;
import com.jfsfeb.airlinereservationsystemwithhibernate.dto.UserDetails;
import com.jfsfeb.airlinereservationsystemwithhibernate.exception.AirlineSystemException;
import com.jfsfeb.airlinereservationsystemwithhibernate.factory.UserFactory;
import com.jfsfeb.airlinereservationsystemwithhibernate.validations.AirlineValidations;

public class AdminServicesImple implements AdminServices {

	AdminDAO adminImple = UserFactory.getAdminDAOImpleInstance();
	AirlineValidations validation = UserFactory.getValidationInstance();

	@Override
	public boolean addNewAdmin(UserDetails admin) {
		if (validation.validateId(admin.getUserId())) {
			if (validation.validateName(admin.getName())) {
				if (validation.validatePhone(admin.getPhoneNumber())) {
					if (validation.validateEmail(admin.getMailId())) {
						if (validation.validatePassword(admin.getPassword())) {
							return adminImple.addNewAdmin(admin);
						}
						throw new AirlineSystemException(
								"Enter Valid Passsword with min 6 Characters, One Uppercase and Lowercase and a Symbol ");
					}
					throw new AirlineSystemException("Enter The Proper Email ID, like abc@gmail.com");
				}
				throw new AirlineSystemException("Phone number should contain 10 numbers");
			}
			throw new AirlineSystemException("Name Should Contains only Alphabets");
		}
		throw new AirlineSystemException("Please Enter valid Id, It Should Contain Exact 4 Digits");
	}

	@Override
	public boolean addFlights(FlightDetails flights) {
		if (validation.validateId(flights.getFlightId())) {
			if (validation.validateName(flights.getFlightName())) {
				if (validation.validateName(flights.getSource())) {
					if (validation.validateName(flights.getDestination())) {
						if (validation.validatePrice(flights.getPrice())) {
							return adminImple.addFlights(flights);
						}
					}
				}
			}
		}

		return false;
	}

	@Override
	public boolean deleteFlights(int id) {
		if (validation.validateId(id)) {
			return adminImple.deleteFlights(id);
		}
		return false;
	}

	@Override
	public List<FlightDetails> searchFlight(int id) {
		if (validation.validateId(id)) {
			return adminImple.searchFlight(id);
		}
		return null;
	}

	@Override
	public List<FlightDetails> viewFlightDetails() {
		return adminImple.viewFlightDetails();
	}

	@Override
	public List<UserDetails> viewAllUserDetails() {
		return adminImple.viewAllUserDetails();
	}

	@Override
	public List<TicketsInfo> viewTicketsInfos() {
		return adminImple.viewTicketsInfos();
	}
}
