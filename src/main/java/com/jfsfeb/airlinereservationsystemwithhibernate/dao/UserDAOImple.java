package com.jfsfeb.airlinereservationsystemwithhibernate.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.jfsfeb.airlinereservationsystemwithhibernate.dto.FlightDetails;
import com.jfsfeb.airlinereservationsystemwithhibernate.dto.TicketsInfo;
import com.jfsfeb.airlinereservationsystemwithhibernate.dto.UserDetails;
import com.jfsfeb.airlinereservationsystemwithhibernate.exception.AirlineSystemException;

public class UserDAOImple implements UserDAO {

	EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("TestPersistence");
	EntityManager entityManager = null;
	EntityTransaction transaction = null;

	@Override
	public boolean userRegistration(UserDetails user) {
		UserDetails users = new UserDetails();

		try {
			entityManager = entityManagerFactory.createEntityManager();
			transaction = entityManager.getTransaction();

			transaction.begin();
			users.setUserId(user.getUserId());
			users.setName(user.getName());
			users.setMailId(user.getMailId());
			users.setPassword(user.getPassword());
			users.setPhoneNumber(user.getPhoneNumber());
			users.setRole("user");
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
	public UserDetails userLogin(String emailId, String password) {
		entityManager = entityManagerFactory.createEntityManager();

		String jpql = "select e from UserDetails e where e.mailId = :mail and e.password = :password";
		Query query = entityManager.createQuery(jpql, UserDetails.class);
		query.setParameter("mail", emailId);
		query.setParameter("password", password);
		UserDetails record = (UserDetails) query.getSingleResult();

		if (record != null) {
			return record;
		}
		entityManager.close();
		throw new AirlineSystemException("Enter correct mailid and password");
	}

	@Override
	public List<FlightDetails> getFlightDetails() {
		entityManager = entityManagerFactory.createEntityManager();
		String jpql = "Select e from FlightDetails e";
		TypedQuery<FlightDetails> query = entityManager.createQuery(jpql, FlightDetails.class);
		List<FlightDetails> recordList = query.getResultList();
		for (int i = 0; i < recordList.size() - 1; i++) {
			recordList.get(i);
		}
		if (recordList.isEmpty()) {
			throw new AirlineSystemException("No flights present");
		}
		entityManager.close();
		return recordList;
	}

	@Override
	public List<FlightDetails> searchFlightByName(String name) {
		entityManager = entityManagerFactory.createEntityManager();

		String jpql = "select e from FlightDetails e where e.flightName = :flightname";
		TypedQuery<FlightDetails> query = entityManager.createQuery(jpql, FlightDetails.class);
		query.setParameter("flightname", name);
		List<FlightDetails> bean = query.getResultList();

		if (bean != null) {
			return bean;
		}
		entityManager.close();
		throw new AirlineSystemException("Flight details not found");
	}

	@Override
	public TicketsInfo bookFligthTicket(TicketsInfo ticket) {
		TicketsInfo tickets = new TicketsInfo();
		try {
			entityManager = entityManagerFactory.createEntityManager();
			transaction = entityManager.getTransaction();

			try {
				FlightDetails record = entityManager.find(FlightDetails.class, ticket.getFlightId());
				if (record != null) {
					try {
						UserDetails record1 = entityManager.find(UserDetails.class, ticket.getUserId());
						if (record1 != null) {
							try {
								transaction.begin();
								tickets.setTicketId(ticket.getTicketId());
								tickets.setUserId(ticket.getUserId());
								tickets.setFlightId(ticket.getFlightId());
								tickets.setNoOfSeatsBooked(ticket.getNoOfSeatsBooked());
								entityManager.persist(tickets);
								transaction.commit();
								return tickets;
							} catch (Exception e) {
								e.getMessage();
								transaction.rollback();
							}
						}
					} catch (Exception e) {
						e.getMessage();
						transaction.rollback();
					}
				}
			} catch (Exception e) {
				e.getMessage();
				transaction.rollback();
			} finally {
				entityManager.close();
			}
			return null;

		} catch (Exception e) {
			e.getMessage();
		}
		throw new AirlineSystemException("Can't book the ticket");
	}

	@Override
	public boolean cancelFlightTicket(int bookingId) {

		entityManager = entityManagerFactory.createEntityManager();
		transaction = entityManager.getTransaction();

		try {
			transaction.begin();
			TicketsInfo record = entityManager.find(TicketsInfo.class, bookingId);
			if (record != null) {
				entityManager.remove(record);
				transaction.commit();
				return true;
			}
			throw new AirlineSystemException("Enter correct booking id");
		} catch (Exception e) {
			e.getMessage();
			transaction.rollback();
		} finally {
			entityManager.close();
		}
		return false;
	}

	@Override
	public List<TicketsInfo> getTicketDetails(int userId) {
		entityManager = entityManagerFactory.createEntityManager();
		String jpql = "Select e from TicketsInfo e where e.userId = :id";
		TypedQuery<TicketsInfo> query = entityManager.createQuery(jpql, TicketsInfo.class);
		query.setParameter("id", userId);
		List<TicketsInfo> records = query.getResultList();
		for (int i = 0; i < records.size() - 1; i++) {
			records.get(i);
		}
		if (records.isEmpty()) {
			throw new AirlineSystemException("No tickets with this userid...........");
		}
		entityManager.close();
		return records;
	}

	@Override
	public List<FlightDetails> searchFlightBySourceAndDestination(String source, String destination) {
		entityManager = entityManagerFactory.createEntityManager();
		String jpql = "select e from FlightDetails e where e.source = :source and e.destination = :destination";
		TypedQuery<FlightDetails> query = entityManager.createQuery(jpql, FlightDetails.class);
		query.setParameter("source", source);
		query.setParameter("destination", destination);
		List<FlightDetails> bean = query.getResultList();
		for (int i = 0; i < bean.size() - 1; i++) {
			bean.get(i);
		}
		if (bean.isEmpty()) {
			throw new AirlineSystemException("No flight found with these details");
		}
		entityManager.close();
		return bean;
	}

}
