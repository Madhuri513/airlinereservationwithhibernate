package com.jfsfeb.airlinereservationsystemwithhibernate.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@SuppressWarnings("serial")

@Entity
@Table(name = "flightinfo")
public class FlightDetails implements Serializable {
	
	@Id
	@Column(name = "flightid")
	private int flightId;
	@Column(name = "flightname")
	private String flightName;
	@Column(name = "arrivaltime")
	private LocalTime arrivalTime;
	@Column(name = "arrivaldate")
	private LocalDate arrivalDate;
	@Column(name = "departuretime")
	private LocalTime departureTime;
	@Column(name = "departuredate")
	private LocalDate departureDate;
	@Column
	private String source;
	@Column
	private String destination;
	@Column
	private int seats;
	@Column
	private double price;

}
