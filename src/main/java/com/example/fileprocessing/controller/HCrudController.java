package com.example.fileprocessing.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.fileprocessing.entity.Employee;
import com.example.fileprocessing.entity.Student;
import com.example.fileprocessing.repo.EmployeeRepo;
import com.example.fileprocessing.repo.StudentRepo;

@RestController
public class HCrudController {
	
	@Autowired
	StudentRepo studentRepo;
	
	@Autowired
	EmployeeRepo empRepo;
	
	@PostMapping(value = "/addStudent")
	public Student createStudent(@RequestBody Student stuObj) {
		try {
			if(stuObj.getId() != null)
				studentRepo.save(stuObj);	
		}
		catch(Exception e)
		{
			System.out.print(e.getLocalizedMessage());
		}
		return stuObj;
	}
	
	@PostMapping(value = "/addEmployee")
	public Employee createEmployee(@RequestBody Employee stuObj) {
		try {
			if(stuObj.getId() != null)
				empRepo.save(stuObj);	
		}
		catch(Exception e)
		{
			System.out.print(e.getLocalizedMessage());
		}
		return stuObj;
	}
	
	@PostMapping(value = "/fetchEmp")
	public List<Employee> fetchEmp() {
		List<Employee> list =new ArrayList<>();
		try {
			
			list=	empRepo.findAll();	
		}
		catch(Exception e)
		{
			System.out.print(e.getLocalizedMessage());
		}
		return list;
	}
	@PostMapping(value = "/deleteStudent")
	public String deleteStudent(@RequestBody Student stuObj) {
		String response = null;
		try {
			if(stuObj.getId() != null)
			{
				Optional<Student> st =studentRepo.findById(stuObj.getId());
				if(st !=null)
				{
					studentRepo.deleteById(st.get().getId());

					response = stuObj.getName() +" has been deleted successfully.";
				}
				else
					response = stuObj.getName() +" does not exist in the database.";

			}
		}
		catch(Exception e)
		{
			System.out.print(e.getLocalizedMessage());
		}
		return response;
	}
	
	@PostMapping(value="updateStudent")
	public String updateStudent(@RequestBody Student stud)
	{
		String res = null;
		Boolean st = studentRepo.findById(stud.getId()).isPresent();
		if(st)
		{
			studentRepo.save(stud);	
			res = "Student "+stud.getName()+ " details updated successfully";
		}
		else
			res = "Student details not found"	;
		return res;
		
	}
	
	@PostMapping(value="/fetchAllStudents")
	public List<Student> fetchAllStudent()
	{
		List<Student> list= new ArrayList<>();
		
		list = studentRepo.findAll();
		return list;
	}
	
}
