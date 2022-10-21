package com.ncu.lab3;

public class DriverLab3 {

	public static void main(String[] args) {
		
		// Customer Management CRUD Application using Hibernate
		
		CustomerDAO customerDAO = new CustomerDAO();
		Customer customer = new Customer(1,"Sahil","Pal","sahil19csu265@ncuindia.edu");
		
		// CRUD operations
		customerDAO.save(customer); 
		Customer c = customerDAO.read(1); 
		System.out.println(c);
		customerDAO.update(1);
		customerDAO.remove(1);
		
	}

}
