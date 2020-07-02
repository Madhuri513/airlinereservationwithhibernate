package com.jfsfeb.airlinereservationsystemwithhibernate.services;

import java.util.List;

import com.jfsfeb.airlinereservationsystemwithhibernate.dao.UserDAO;
import com.jfsfeb.airlinereservationsystemwithhibernate.dto.FlightDetails;
import com.jfsfeb.airlinereservationsystemwithhibernate.dto.TicketsInfo;
import com.jfsfeb.airlinereservationsystemwithhibernate.dto.UserDetails;
import com.jfsfeb.airlinereservationsystemwithhibernate.exception.AirlineSystemException;
import com.jfsfeb.airlinereservationsystemwithhibernate.factory.UserFactory;
import com.jfsfeb.airlinereservationsystemwithhibernate.validations.AirlineValidations;

public class UserServicesImple implements UserServices {

	UserDAO userImple = UserFactory.getUserDAOImpleInstance();
	AirlineValidations validation = UserFactory.getValidationInstance();

	@Override
	public boolean userRegistration(UserDetails user) {
		if (validation.validateId(user.getUserId())) {
			if (validation.validateName(user.getName())) {
				if (validation.validatePhone(user.getPhoneNumber())) {
					if (validation.validateEmail(user.getMailId())) {
						if (validation.validatePassword(user.getPassword())) {
							return userImple.userRegistration(user);
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
	public UserDetails userLogin(String emailId, String password) {
		if (validation.validateEmail(emailId)) {
			if (validation.validatePassword(password)) {
				return userImple.userLogin(emailId, password);
			}
			throw new AirlineSystemException(
					"Password is incorrect, please give password that entered while registering");
		}
		throw new AirlineSystemException("Email is incorrect, please give registered emailid");
	}

	@Override
	public List<FlightDetails> getFlightDetails() {
		return userImple.getFlightDetails();
	}

	@Override
	public List<FlightDetails> searchFlightByName(String name) {
		if (validation.validateName(name)) {
			return userImple.searchFlightByName(name);
		}
		throw new AirlineSystemException("Enter correct name");
	}

	@Override
	public List<FlightDetails> searchFlightBySourceAndDestination(String source, String destination) {
		if (validation.validateName(source)) {
			if (validation.validateName(destination)) {
				return userImple.searchFlightBySourceAndDestination(source, destination);
			}
			throw new AirlineSystemException("Enter correct destination");
		}
		throw new AirlineSystemException("Enter correct source");
	}

	@Override
	public TicketsInfo bookFligthTicket(TicketsInfo ticket) {
		if (ticket != null) {
			if (validation.validateId(ticket.getTicketId())) {
				if (validation.validateId(ticket.getUserId())) {
					if (validation.validateId(ticket.getFlightId())) {
						return userImple.bookFligthTicket(ticket);
					}
				}
			}
		}
		throw new AirlineSystemException("Enter correct details");
	}

	@Override
	public boolean cancelFlightTicket(int ticketId) {
		if (validation.validateId(ticketId)) {
			return userImple.cancelFlightTicket(ticketId);
		}
		throw new AirlineSystemException("Enter id correctly");
	}

	@Override
	public List<TicketsInfo> getTicketDetails(int userId) {
		if (validation.validateId(userId)) {
			return userImple.getTicketDetails(userId);
		}
		throw new AirlineSystemException("Enter correct id");
	}

}
