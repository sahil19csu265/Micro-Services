package com.ncu.lab1;

public class DriverLab1 {

	public static void main(String[] args) {
		
		StudentDAO stdDao = new StudentDAO();
		Student student = new Student(1,"Sahil","Pal","sahil19csu265@ncuindia.edu");
		stdDao.save(student); 
		stdDao.read(1); 
		stdDao.update(1);
		stdDao.remove(1);
	}

}
