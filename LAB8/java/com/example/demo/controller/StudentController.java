package com.example.demo.controller;

import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Student;
import com.example.demo.service.StudentServices;

import java.util.*;

@RestController
public class StudentController {
	
	@Autowired
	StudentServices studentService;

	@RequestMapping(path = "/add",method = RequestMethod.POST)
	public String addStudent(@RequestBody Student student) {
		if(studentService.add(student)) {
			return "Student added Successfully!";
		}
		else {
			return "Student failed to add !";
		}
	}
	
	@GetMapping(path = "/getStudent")
	public Student getStudent() {
		return studentService.getStudentRecord();
	}
	
	@GetMapping(path = "/getStudentList")
	public List<Student> getStudentList(){
		return studentService.getStudentRecordList();
	}
	
	@GetMapping(path = "/getStudentByID/{studentID}")
	public Map<String,Student> getStudentById(@PathVariable int studentID) {
		Student student = studentService.find(studentID);
		if(student != null) {
			return new HashMap<String,Student>() {
				{
					put("result",student);
				}
			};
		}
		else {
			return new HashMap<String,Student>() {
				{
					put("result",null);
				}
			};
		}
	}
	
	@PutMapping("/update/{studentID}")
	public String updateStudentRecord(@PathVariable int studentID, @RequestBody Student student) {
		if(studentService.updateRecordByID(studentID, student)) {
			return "Record Updated Successfully !";
		}
		else {
			return "Failed to Update Record !";
		}	 
	}
	
	@DeleteMapping("/delete/{studentID}")
	public String deleteStudentRecord(@PathVariable int studentID) {
		return studentService.deleteRecordByID(studentID)
				?
				"Record Deleted Successfully !" 
				: "Record Failed to Delete !";
	}
	
}
