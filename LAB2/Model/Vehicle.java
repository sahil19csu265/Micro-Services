package com.ncu.lab2.Model;

import javax.persistence.*;
import java.util.*;

@Entity
public class Vehicle {

	@Id @GeneratedValue
	private int vehicleID;
	private String vehicleName;
	
	@ManyToMany
	private List<Person> personList = new ArrayList<Person>();
	
	
	public Vehicle() {
		
	}
	
	public List<Person> getPersonList() {
		return personList;
	}

	public void setPersonList(List<Person> personList) {
		this.personList = personList;
	}
	public Vehicle(int vehicleID, String vehicleName) {
		super();
		this.vehicleID = vehicleID;
		this.vehicleName = vehicleName;
	}
	public int getVehicleID() {
		return vehicleID;
	}
	public void setVehicleID(int vehicleID) {
		this.vehicleID = vehicleID;
	}
	public String getVehicleName() {
		return vehicleName;
	}
	public void setVehicleName(String vehicleName) {
		this.vehicleName = vehicleName;
	}
	
	
}
