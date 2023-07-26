package com.lti.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.lti.dao.UserDao;
import com.lti.dto.RegDto;
import com.lti.dto.UpdateUser;
import com.lti.entity.User;
import com.lti.exception.USerNotFoundException;
import com.lti.exception.UserIdMissingException;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;
	
	@Autowired
	EmailService service;
	public RegDto signup(User user) {
		try {
			User user2 = userDao.addOrUpdateUser(user);
//			String email = user2.getEmail();
			String text = "Registration successful. Your generated User ID is "+user2.getUserID();
//			String subject = "Registration confirmation";
//			emailService.sendEmailForSignup(email, text, subject);							
//			System.out.println("Email sent.");
//			return "Sign up successful. Your userId is:" + user2.getUserId(); 
			RegDto dto = new RegDto();
			dto.setUserId(user2.getUserID());
			dto.setMessage(text);
			return dto;
		} catch (Exception e) {
			RegDto dto = new RegDto();
			dto.setUserId(0);
			dto.setMessage("Unexpected error occured. Signup failed");
			return dto;
		}
	}

	public UpdateUser updateProfile(User user) {
		UpdateUser updateUser= new UpdateUser();
		try {
			
			if(user.getUserID()==0)
			{
				throw new UserIdMissingException("Please Mention USerId");
			}
			else if(userDao.searchUserById(user.getUserID())==null)
			{
				throw new USerNotFoundException("User Not Found.");
			}
			else
			{
				User u = userDao.addOrUpdateUser(user);
				updateUser.setMessage("profile updated");
				updateUser.setUser(u);
				return updateUser;
			}
		}
		catch(Exception e)
		{
			updateUser.setMessage(e.getMessage());
			return updateUser;
		}
	}

	public User findUser(int userId) {
		// TODO Auto-generated method stub
		return userDao.searchUserById(userId);
	}

	public List<User> viewAllUsers() {
		// TODO Auto-generated method stub
		return userDao.viewAllUsers();
	}

	public boolean userLogin(int userId, String password) {
		// TODO Auto-generated method stub
		return userDao.login(userId, password);
	}

	public String getUserName(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
