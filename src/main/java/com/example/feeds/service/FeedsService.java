package com.example.feeds.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fileprocessing.entity.Feeds;
import com.example.fileprocessing.entity.User;
import com.example.fileprocessing.repo.FeedsRepo;
import com.example.fileprocessing.repo.UserRepo;

@Service
public class FeedsService {
	
	@Autowired
	FeedsRepo feedsRepo;
	
	@Autowired
	UserRepo userRepo;
	private final org.slf4j.Logger log =  LoggerFactory.getLogger(this.getClass());

	public String saveFeed(Feeds  feed)
	{
		try {
			if(feed != null)
			{
				feedsRepo.save(feed);
			}
		}
		catch(Exception e)
		{
			return "Error in saving feed";
		}
		return "Feed successfully saved.";
	}
	
	
	public List<Feeds> fetchAllActiveFeeds()
	
	{
		List<Feeds> listFeeds = new ArrayList<>();
		
		try {
			listFeeds = feedsRepo.findAll();
		}
		catch(Exception e)
		{
			System.out.print("Error in fetching feeds:  "+e.getLocalizedMessage());
		}
		return listFeeds;
	}
	
	
	
	public Feeds fetchFeedById(Long id) {
		Feeds response = null;
		try {
			if(id != null)
			{
				Optional<Feeds>	dd  = feedsRepo.findById(id);
				if(dd.isPresent())
				{
					response = dd.get();
				}
			}
		}
		catch(Exception e)
		{
			
		}
		return response;
	}


	public void loadUsers() {
		User user = new User();
		user.setUserId("1");
		user.setUserName("user_1");
		user.setEmailId("user1@gmail.com");
		user.setPassword("1234");
		userRepo.save(user);
		
		
	}
	
	
	public User validateUser(String userId,String password)
	{
		User user = null;
		try {
			if(userId != null)
			{
				Optional<User> opUser = userRepo.findById(userId);
			
				if(opUser.isPresent())
				{
					if(password != null)
					{
						if(opUser.get().getPassword().equals(password))
							user = opUser.get();
					}
					
				}
			}
			
		}catch(Exception e)
		{
			log.error("In validate user service :  "+e.getLocalizedMessage());
		}
		return  user;
	}


	public String addNewUser(String userId, String emailId, String password) {
		
	String message = "";
		try
		{
			User user = new User();
			user.setUserId(userId);
			user.setUserName(userId);
			user.setEmailId(emailId);
			user.setPassword(password);
			userRepo.save(user);
			message = "Account created successfully for the user "+userId;
		}
		catch(Exception e)
		{
			log.error("In validate user service :  "+e.getLocalizedMessage());

		}
		return message;
	}

}
