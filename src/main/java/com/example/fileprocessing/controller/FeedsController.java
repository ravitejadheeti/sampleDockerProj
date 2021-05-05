package com.example.fileprocessing.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.feeds.service.FeedsService;
import com.example.fileprocessing.entity.Feeds;
import com.example.fileprocessing.entity.User;

@Controller
public class FeedsController {

	@Value("${uploadDir}")
	private String uploadFolder;

	@Autowired
	private FeedsService feedsService;


	/*
	 * @GetMapping(value = {"/", "/home"}) public String loginPage() {
	 * feedsService.loadUsers(); return "index"; }
	 */
	@GetMapping(value = {"/", "/addFeed"})
	public String addProductPage() {
		return "index";
	}
	private final org.slf4j.Logger log =  LoggerFactory.getLogger(this.getClass());

	
	
	@PostMapping("/image/saveFeedDetails")
	public @ResponseBody ResponseEntity<?> saveFeedDetails(@RequestParam("name") String name,
			 @RequestParam("description") String description, Model model, HttpServletRequest request
			,final @RequestParam("image") MultipartFile file) {
		try {
			//String uploadDirectory = System.getProperty("user.dir") + uploadFolder;
			String uploadDirectory = request.getServletContext().getRealPath(uploadFolder);
			log.info("uploadDirectory:: " + uploadDirectory);
			String fileName = file.getOriginalFilename();
			String filePath = Paths.get(uploadDirectory, fileName).toString();
			log.info("FileName: " + file.getOriginalFilename());
			if (fileName == null || fileName.contains("..")) {
				model.addAttribute("invalid", "Sorry! Filename contains invalid path sequence \" + fileName");
				return new ResponseEntity<>("Sorry! Filename contains invalid path sequence " + fileName, HttpStatus.BAD_REQUEST);
			}
			String[] names = name.split(",");
			String[] descriptions = description.split(",");
			Date createDate = new Date();
			log.info("Name: " + names[0]+" "+filePath);
			log.info("description: " + descriptions[0]);
			try {
				File dir = new File(uploadDirectory);
				if (!dir.exists()) {
					log.info("Folder Created");
					dir.mkdirs();
				}
				// Save the file locally
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
				stream.write(file.getBytes());
				stream.close();
			} catch (Exception e) {
				log.info("in catch");
				e.printStackTrace();
			}
			byte[] imageData = file.getBytes();
			Feeds feeds = new Feeds();
			feeds.setFeedTitle(names[0]);
			feeds.setImage(imageData);
			feeds.setDescription(descriptions[0]);
			feeds.setCreateDate(createDate);
			String response = feedsService.saveFeed(feeds);
			log.info("HttpStatus===" + new ResponseEntity<>(HttpStatus.OK));
			return new ResponseEntity<>(response+" " + fileName, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception: " + e);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}	
	
	@GetMapping("/image/display/{id}")
	@ResponseBody
	void showImage(@PathVariable("id") Long id, HttpServletResponse response, Optional<Feeds> feed)
			throws ServletException, IOException {
		log.info("Id :: " + id);
		feed = Optional.ofNullable(feedsService.fetchFeedById(id));
		response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
		response.getOutputStream().write(feed.get().getImage());
		response.getOutputStream().close();
	}

	@GetMapping("/image/showFeedDetails")
	String showFeedDetails(@RequestParam("id") Long id, Optional<Feeds> feed, Model model) {
		try {
			log.info("Id :: " + id);
			if (id != 0) {
				feed = Optional.ofNullable(feedsService.fetchFeedById(id));
			
				log.info("products :: " + feed);
				if (feed.isPresent()) {
					model.addAttribute("id", feed.get().getId());
					model.addAttribute("description", feed.get().getDescription());
					model.addAttribute("feedTitle", feed.get().getFeedTitle());
					return "imagedetails";
				}
				return "redirect:/home";
			}
		return "redirect:/home";
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/home";
		}	
	}

	@GetMapping("/image/show")
	String show(Model map) {
		List<Feeds> images = feedsService.fetchAllActiveFeeds();
		map.addAttribute("images", images);
		return "images";
	}
	
	@PostMapping("/image/validateUser")
	String validateUser(@RequestParam("userId") String userId,@RequestParam("password") String password, Model model,HttpServletRequest request)
	{		
		User user = feedsService.validateUser(userId, password);
		if(user != null)
			model.addAttribute("user",user);
		else
			model.addAttribute("Invalid username / password entered.");
		return "createfeed";
	}
	
	
	@PostMapping("/image/addNewUser")
	String addNewUser(@RequestParam("username") String userId,@RequestParam("emailId") String emailId,@RequestParam("password") String password, Model model,HttpServletRequest request)
	{		
		String response = feedsService.addNewUser(userId,emailId, password);
		model.addAttribute(response);
			
		return "index";
	}
}
