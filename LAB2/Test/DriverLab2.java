package com.ncu.lab2.Test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.ncu.lab2.Model.Person;
import com.ncu.lab2.Model.Vehicle;

public class DriverLab2 {

	public static void main(String args[]) {
		
		Person person = new Person(1,"Sahil Pal");
		Vehicle vehicle1 = new Vehicle(101,"Honda City");
		Vehicle vehicle2 = new Vehicle(102,"Thar");
		
		// mapping both vehicles to the person object
		person.getVehicle().add(vehicle1);
		person.getVehicle().add(vehicle2);
		
		// mapping of owner to vehicle object
		vehicle1.getPersonList().add(person);
		vehicle2.getPersonList().add(person);
		
		// DAO WORK
		SessionFactory sessionFact = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		Session session = sessionFact.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.save(vehicle1);
			session.save(vehicle2);
			session.save(person);
			transaction.commit();
			System.out.println("Person and Vehicle saved !");
		}
		catch(Exception e) {
			System.out.println("Failed to Saved Record ");
		}
		finally{
			session.close();
		}
	}
}
