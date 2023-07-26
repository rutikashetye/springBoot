package com.lti.dao;

import java.util.List;

import com.lti.dto.RegDto;
import com.lti.entity.User;


public interface UserDao {
User addOrUpdateUser(User user );
User searchUserById(int userID);
List<User> viewAllUsers();
boolean login(int userId, String password);
}
