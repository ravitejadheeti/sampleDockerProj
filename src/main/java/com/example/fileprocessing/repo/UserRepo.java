package com.example.fileprocessing.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fileprocessing.entity.User;

public interface UserRepo extends JpaRepository<User, String> {

}
