package com.example.fileprocessing.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.fileprocessing.entity.Feeds;

@Repository
public interface FeedsRepo extends JpaRepository<Feeds, Long> {

}
