package com.jfsfeb.airlinereservationsystemwithhibernate.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;


@Data
@SuppressWarnings("serial")

@Entity
@Table(name = "ticketsinfo")
public class TicketsInfo implements Serializable {
	
	@Id
	@Column(name = "ticketid")
	private int ticketId; 
	@Column(name="user_id")
	private int userId;
	@Column(name="flight_id")
	private int flightId;
	@Column(name = "seats")
	private int noOfSeatsBooked;

}
