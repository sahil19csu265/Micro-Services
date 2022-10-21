package com.ncu.lab2.Model;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name="Person")
public class Person {
	
	@Id
	private int id;
	@Column(name = "name", length = 25, nullable = false, unique = true)
	private String fullName;
	
	@ManyToMany
	private List<Vehicle> vehicle = new ArrayList<Vehicle>();
	
	public Person() {
		
	}
	
	public Person(int id, String fullName) {
		super();
		this.id = id;
		this.fullName = fullName;
	}
	
	public List<Vehicle> getVehicle() {
		return vehicle;
	}
	public void setVehicle(List<Vehicle> vehicle) {
		this.vehicle = vehicle;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	
}
