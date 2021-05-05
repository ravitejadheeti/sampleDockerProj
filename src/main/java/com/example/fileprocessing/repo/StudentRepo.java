package com.example.fileprocessing.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.fileprocessing.entity.Student;

@Repository
public interface StudentRepo extends JpaRepository<Student, Integer> {
	

	Optional<Student> findById(Integer id);

	void deleteById(Optional<Student> st);

}
