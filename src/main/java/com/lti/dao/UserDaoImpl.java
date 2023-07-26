package com.lti.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lti.entity.User;

@Repository
public class UserDaoImpl implements UserDao{
		@PersistenceContext
		EntityManager em;
	
		@Transactional
		public User addOrUpdateUser(User user) {
			User userPersisted = em.merge(user);
			return userPersisted;
		}

		public User searchUserById(int userId) {
			return em.find(User.class, userId);
		}

		public List<User> viewAllUsers() {
			String jpql = "select u from User u";
			TypedQuery<User> query = em.createQuery(jpql, User.class);
			return query.getResultList();
		}

		public boolean login(int userId, String password) {
			User u = searchUserById(userId);
			if(u!=null)
			{
				if(u.getUserID()==userId && u.getPassword().equals(password))
				{
					return true;
				}
			}		
			return false;
		}

	}


