package com.ncu.lab3;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class CustomerDAO {
	
	SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
	
	public void save(Customer student) {
		Session session = sessionFactory.openSession();
		Transaction trans = session.beginTransaction();
		session.save(student);
		trans.commit();
		session.close();
		System.out.println("Data saved successfully!");
	}
	
	public void remove(int id) {
		Session session = sessionFactory.openSession();
		Customer std = read(id);
		if(std == null) {
			System.out.println("This ID do not exists!");
			return;
		}
		Transaction trans = session.beginTransaction();
		session.delete(std);
		trans.commit();
		session.close();
		System.out.println("Record Deleted Successfully!");
	}
	
	public void update(int id) {
		Session session = sessionFactory.openSession();
		Customer std = read(id);
		if(std == null) {
			System.out.println("This ID do not exists!");
			return;
		}
		std.setEmail("sahilnewemail@gmail.com");
		Transaction trans = session.beginTransaction();
		session.update(std);
		trans.commit();
		session.close();
		System.out.println("Record Updated!");
	}
	
	public Customer read(int id) {
			Session session = sessionFactory.openSession();
			Customer student = session.get(Customer.class, id);
			System.out.println("Customer Data is: "+student);
			session.close();
			return student;
	}
	

}
