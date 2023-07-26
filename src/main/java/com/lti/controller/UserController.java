package com.lti.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.LoginData;
import com.lti.dto.ProfilePicDto;
import com.lti.dto.RegDto;
import com.lti.dto.UpdateUser;
import com.lti.entity.User;
import com.lti.exception.USerNotFoundException;
import com.lti.service.UserService;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {
	
	// http methods :put - update, post - insert, fetch-get, delete - delete
	

	@Autowired
	UserService service;
	
	@RequestMapping(value="/signup",method=RequestMethod.POST)
	public RegDto signup(@RequestBody User user) {
//		String m=service.signup(user); 
//	    return m;
		return service.signup(user);
	}
	
	@PutMapping("/update")
	public UpdateUser updateProfile(@RequestBody User u)
	{
		return service.updateProfile(u);
	}
	
//	@PostMapping("/update")
//	public UpdateUser updateProfile(@RequestBody User u)
//	{
//		return service.updateProfile(u);
//	}
//	
	@GetMapping("/user/{userId}")
	public User search(@PathVariable int userId)
	{
		return service.findUser(userId);
	}
	
	@GetMapping("viewall")
	public List<User> viewAllUsers()
	{
		return service.viewAllUsers();
	}
	
	@PostMapping("/login")
	public boolean login(@RequestBody LoginData l)
	{
		boolean isValid = service.userLogin(l.getUserId(), l.getPassword());
		return isValid;
	}
	
	@PostMapping("/pic-upload")
    public String upload(ProfilePicDto profilePicDto) {
        String imageUploadLocation = "d:/uploads/";
        String fileName = profilePicDto.getProfilePic().getOriginalFilename();
        String targetFile = imageUploadLocation + fileName;
        try {
            FileCopyUtils.copy(profilePicDto.getProfilePic().getInputStream(), new FileOutputStream(targetFile));
        } catch (IOException e) {
            e.printStackTrace();
            return e.getMessage();
        }
        User user = service.findUser(profilePicDto.getUserId());
        user.setProfilePic(fileName);
        UpdateUser updateUser= service.updateProfile(user);
        if(updateUser!=null)
            return "File uploaded";

        return "Upload failed";
    }
	
	@GetMapping("/profile")
    public User profile(@RequestParam("userId") int id, HttpServletRequest request) {
        //fetching customer data from the database
        User user = service.findUser(id);

        //reading the project's deployed folder location
        String projPath = request.getServletContext().getRealPath("/");
        String tempDownloadPath = projPath + "/downloads/";
        //creating a folder within the project where we will place the profile pic of the customer getting fetched
        File f = new File(tempDownloadPath);
        if(!f.exists())
            f.mkdir();
        String targetFile = tempDownloadPath + user.getProfilePic();

        //the original location where the uploaded images are present
        String uploadedImagesPath = "d:/uploads/";
        String sourceFile = uploadedImagesPath + user.getProfilePic();

        try {
            FileCopyUtils.copy(new File(sourceFile), new File(targetFile));
        } catch (IOException e) {
            e.printStackTrace();
            //maybe for this customer there is no profile pic
        }

        return user;
    }
}
