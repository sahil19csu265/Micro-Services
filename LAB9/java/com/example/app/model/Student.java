package com.example.app.model;

import javax.persistence.*;

@Entity
@Table(name="STUDENT")
public class Student {

	@Id @GeneratedValue
	private int id;
	private String name;
	private boolean isDisabled;
	
	public Student() {
		super();
	}
	
	public Student(int id,String name,boolean isDisabled) {
		this.id = id;
		this.name = name;
		this.isDisabled = isDisabled;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isDisabled() {
		return isDisabled;
	}
	public void setDisabled(boolean isDisabled) {
		this.isDisabled = isDisabled;
	}
	
	
}
