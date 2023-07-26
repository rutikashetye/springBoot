package com.lti.service;

import java.util.List;

import com.lti.dto.RegDto;
import com.lti.dto.UpdateUser;
import com.lti.entity.User;


public interface UserService {
 RegDto signup(User user);
 UpdateUser updateProfile(User user);
 User findUser(int userId);
 boolean userLogin(int userId, String password);
 String getUserName(int userId);
 List<User> viewAllUsers();
}
