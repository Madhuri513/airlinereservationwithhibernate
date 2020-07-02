package com.jfsfeb.airlinereservationsystemwithhibernate.factory;

import com.jfsfeb.airlinereservationsystemwithhibernate.dao.AdminDAO;
import com.jfsfeb.airlinereservationsystemwithhibernate.dao.AdminDAOImple;
import com.jfsfeb.airlinereservationsystemwithhibernate.dao.UserDAO;
import com.jfsfeb.airlinereservationsystemwithhibernate.dao.UserDAOImple;
import com.jfsfeb.airlinereservationsystemwithhibernate.services.AdminServices;
import com.jfsfeb.airlinereservationsystemwithhibernate.services.AdminServicesImple;
import com.jfsfeb.airlinereservationsystemwithhibernate.services.UserServices;
import com.jfsfeb.airlinereservationsystemwithhibernate.services.UserServicesImple;
import com.jfsfeb.airlinereservationsystemwithhibernate.validations.AirlineValidations;
import com.jfsfeb.airlinereservationsystemwithhibernate.validations.Validation;

public class UserFactory {
	
	private UserFactory() {
	}

	public static AdminDAO getAdminDAOImpleInstance() {
		AdminDAO dao = new AdminDAOImple();
		return dao;
	}

	public static UserDAO getUserDAOImpleInstance() {
		UserDAO userDao = new UserDAOImple();
		return userDao;
	}

	public static AdminServices getAdminServicesImpleInstance() {
		AdminServices adminServices = new AdminServicesImple();
		return adminServices;
	}

	public static UserServices getUserServicesImpleInstance() {
		UserServices userServices = new UserServicesImple();
		return userServices;
	}

	public static AirlineValidations getValidationInstance() {
		AirlineValidations validation = new Validation();
		return validation;
	}
	
	

}
