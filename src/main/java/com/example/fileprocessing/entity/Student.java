package com.example.fileprocessing.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "student")
public class Student {
	
	@Id
	@Column(name="id")
	Integer id;
	
	@Column(name="name")
	String name;
	
	@Column(name="test_score")
	Integer testScore;
	
	public Integer getTestScore() {
		return testScore;
	}

	public void setTestScore(Integer testScore) {
		this.testScore = testScore;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

}
