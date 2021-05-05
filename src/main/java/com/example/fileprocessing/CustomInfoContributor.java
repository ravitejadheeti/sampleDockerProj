package com.example.fileprocessing;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import com.example.fileprocessing.repo.StudentRepo;

@Component
public class CustomInfoContributor implements InfoContributor {

    @Autowired
    StudentRepo stuRepo;

    @Override
    public void contribute(Info.Builder builder) {
        Map<String, Integer> userDetails = new HashMap<>();
        userDetails.put("active", (int) stuRepo.count());
        builder.withDetail("users", userDetails);
    }

}