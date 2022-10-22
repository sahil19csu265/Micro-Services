package com.example.demo.service;

import com.example.demo.model.Student;
import java.util.*;

import org.springframework.stereotype.Repository;

@Repository
public class StudentServices {

	public boolean add(Student student) {
		// call to DAO
		return true;
	}
	
	public Student getStudentRecord() {
		return new Student(1,"Sahil Pal",false);
	}
	
	public List<Student> getStudentRecordList(){
		return new ArrayList<Student>() {
			{
				add(new Student(2,"Sahil Pal",false));
				add(new Student(3,"Raghavender Singh",true));
				add(new Student(4,"Sahil Bhatia",false));
			}
		};
	}
	
	public Student find(int id) {
		if(id == 1) {
			return new Student(1,"Sahil Pal",false);
		}
		else {
			return null;
		}
	}
	
	public boolean updateRecordByID(int id,Student student) {
		// DAO call to update in DB
		return true;
	}
	
	public boolean deleteRecordByID(int id) {
		// DAO call to delete in DB
		return true;
	}
}
