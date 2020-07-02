package com.jfsfeb.airlinereservationsystemwithhibernate.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

@Data
@SuppressWarnings("serial")

@Entity
@Table(name = "userinfo")
public class UserDetails implements Serializable {
	
	@Id
	@Column(name = "id")
	private int userId;
	@Column
	private String name;
	@Column(name = "email")
	private String mailId;
	@ToString.Exclude
	@Column 
	private String password;
	@Column(name = "phonenumber")
	private long phoneNumber;
	@Column
	private String role;

}
