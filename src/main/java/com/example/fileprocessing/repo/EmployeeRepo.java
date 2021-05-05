package com.example.fileprocessing.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fileprocessing.entity.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Integer> {
	
	Optional<Employee> findById(Integer id);

}
