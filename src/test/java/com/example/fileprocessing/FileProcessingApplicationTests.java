package com.example.fileprocessing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.fileprocessing.entity.Student;
import com.example.fileprocessing.repo.StudentRepo;

@SpringBootTest
class FileProcessingApplicationTests {

	@Autowired
	StudentRepo stuRepo;
	
	@Test
	void contextLoads() {
	}
	
	@Test
	public void testAddStudent()
	{
		Student st = new Student();
		st.setId(5);
		st.setName("TCS");
		st.setTestScore(95);		
		stuRepo.save(st);
		assertNotNull(st);
	}
	
	
	
	@Test
	public void testUpdateStudent()
	{
		Student st = new Student();
		st.setId(5);
		st.setName("TATA");
		st.setTestScore(95);	
		Boolean opst = stuRepo.findById(st.getId()).isPresent();
		if(opst)
			stuRepo.save(st);
		assertNotNull(opst);
	}
	
	
	@Test
	public void testFindAllStudents()
	{	
		 stuRepo.findAll();
	
	}
	@Test
	public void testDeleteStudent()
	{
		Student st = new Student();
		st.setId(5);
		Optional<Student> opst = stuRepo.findById(st.getId());
		stuRepo.deleteById(opst.get().getId());
		assertNotNull(opst);
	}
}
