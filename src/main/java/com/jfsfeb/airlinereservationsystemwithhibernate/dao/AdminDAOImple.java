package com.jfsfeb.airlinereservationsystemwithhibernate.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.jfsfeb.airlinereservationsystemwithhibernate.dto.FlightDetails;
import com.jfsfeb.airlinereservationsystemwithhibernate.dto.TicketsInfo;
import com.jfsfeb.airlinereservationsystemwithhibernate.dto.UserDetails;
import com.jfsfeb.airlinereservationsystemwithhibernate.exception.AirlineSystemException;

public class AdminDAOImple implements AdminDAO {

	EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("TestPersistence");
	EntityManager entityManager = null;
	EntityTransaction transaction = null;

	@Override
	public boolean addNewAdmin(UserDetails admin) {
		UserDetails users = new UserDetails();
		try {
			entityManager = entityManagerFactory.createEntityManager();
			transaction = entityManager.getTransaction();
			users.setUserId(admin.getUserId());
			users.setName(admin.getName());
			users.setMailId(admin.getMailId());
			users.setPassword(admin.getPassword());
			users.setPhoneNumber(admin.getPhoneNumber());
			users.setRole(admin.getRole());
			entityManager.persist(users);
			transaction.commit();
			return true;

		} catch (Exception e) {
			e.getMessage();
			transaction.rollback();
		} finally {
			entityManager.close();
		}
		throw new AirlineSystemException("Invalid details");		
	}

	@Override
	public boolean addFlights(FlightDetails flights) {
		FlightDetails flight1 = new FlightDetails();

		try {
			entityManager = entityManagerFactory.createEntityManager();
			transaction = entityManager.getTransaction();

			transaction.begin();
			flight1.setFlightId(flights.getFlightId());
			flight1.setFlightName(flights.getFlightName());
			flight1.setArrivalDate(flights.getArrivalDate());
			flight1.setArrivalTime(flights.getArrivalTime());
			flight1.setDepartureDate(flights.getDepartureDate());
			flight1.setDepartureTime(flights.getArrivalTime());
			flight1.setSource(flights.getSource());
			flight1.setDestination(flights.getDestination());
			flight1.setSeats(flights.getSeats());
			flight1.setPrice(flights.getPrice());
			entityManager.persist(flight1);
			transaction.commit();
			return true;

		} catch (Exception e) {
			e.getMessage();
			transaction.rollback();
		}
		entityManager.close();
		throw new AirlineSystemException("Invalid details");
	}

	@Override
	public boolean deleteFlights(int id) {
		entityManager = entityManagerFactory.createEntityManager();
		transaction = entityManager.getTransaction();

		try {
			transaction.begin();
			FlightDetails record = entityManager.find(FlightDetails.class, id);
			if (record != null) {
				entityManager.remove(record);
				transaction.commit();
				return true;
			}
			throw new AirlineSystemException("Enter correct flight id");
		} catch (Exception e) {
			e.getMessage();
			transaction.rollback();
		} finally {
			entityManager.close();
		}
		return false;
	}

	@Override
	public List<FlightDetails> searchFlight(int id) {
		entityManager = entityManagerFactory.createEntityManager();

		String jpql = "select e from FlightDetails e where e.flightId = :flightid";
		TypedQuery<FlightDetails> query = entityManager.createQuery(jpql, FlightDetails.class);
		query.setParameter("flightid", id);
		List<FlightDetails> bean = query.getResultList();

		if (bean != null) {
			return bean;
		}
		throw new AirlineSystemException("Flight details not found");
	}

	@Override
	public List<FlightDetails> viewFlightDetails() {
		entityManager = entityManagerFactory.createEntityManager();
		String jpql = "Select e from FlightDetails e";
		TypedQuery<FlightDetails> query = entityManager.createQuery(jpql, FlightDetails.class);
		List<FlightDetails> recordList = query.getResultList();
		entityManager.close();
		return recordList;
	}

	@Override
	public List<UserDetails> viewAllUserDetails() {
		entityManager = entityManagerFactory.createEntityManager();
		String jpql = "Select e from UserDetails e";
		TypedQuery<UserDetails> query = entityManager.createQuery(jpql, UserDetails.class);
		List<UserDetails> records = query.getResultList();
		entityManager.close();
		return records;
	}

	@Override
	public List<TicketsInfo> viewTicketsInfos() {
		entityManager = entityManagerFactory.createEntityManager();
		String jpql = "Select e from TicketsInfo e";
		TypedQuery<TicketsInfo> query = entityManager.createQuery(jpql, TicketsInfo.class);
		List<TicketsInfo> records = query.getResultList();
		entityManager.close();
		return records;
	}

}
