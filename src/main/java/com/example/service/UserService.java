package com.example.service;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.dom4j.util.StringUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.User;
import com.example.repository.UserReponsitory;

@Service
public class UserService {
	
	 @Autowired
	 UserReponsitory userReponsitory;

	    public List<User> findAll()
	    {
	        List<User> userList = userReponsitory.findAll();
	         
	        if(userList.size() > 0) {
	            return userList;
	        } else {
	            return new ArrayList<User>();
	        }
	    }
	     
	    public User getUserById(Integer id) 
	    {
	        Optional<User> user = userReponsitory.findById(id);	         
	        return user.get();

	    }
	    
	    
	    public User getUserByEmail(String email) 
	    {
	    	Optional<User> user = userReponsitory.findByUserName(email);	   
	    	if(user.isPresent()) {
	    		return user.get();
	        } else {
	        	return null;
	        }

	    }
	    
	    
	    public User getUserByEmailHQL(String email) 
	    { 	
//			SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
//			Session session = sessionFactory.openSession();
//			session.beginTransaction();		
//	    	User user = null;
//	    	Query query = session.getNamedQuery("HQL_GET_USER_NAME");
//	    	 List list = query.list();
//	        session.getTransaction().commit();
//	        session.close();
	        
	        EntityManagerFactory emf = Persistence.createEntityManagerFactory("mnf");
	        EntityManager em = emf.createEntityManager();
	        em.getTransaction().begin();
	        
	        List<User> list = em.createNamedQuery("User.findAll", User.class).getResultList();
	       
	        if (list != null && list.size() > 0) {
	        	User user = (User) list.get(0);
	        	System.out.println("kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk"+ user.getUserName());  
	        	return user;
	        }
	        else {
		            return null;
		    }
	    	 
	    	 
	    }
	    

	     
	    public void save(User entity) 
	    {
	    	try {
				userReponsitory.save(entity);
			} catch (Exception e) {
				System.out.println("Lỗi Save User ");
			}
	    }
	    
	    public User createOrUpdateUser(User entity)
	    {
	        Optional<User> user = userReponsitory.findById(entity.getUserId());
	         
	        if(user.isPresent())
	        {
	            User newEntity = user.get();
	            newEntity.setUserId(entity.getUserId());
	            newEntity.setUserName(entity.getUserName());
	            newEntity.setPassword(entity.getPassword());
	 
	            newEntity = userReponsitory.save(newEntity);
	             
	            return newEntity;
	        } else {
	            entity = userReponsitory.save(entity);
	            return entity;
	        }
	    }
	     
	    public void deleteEmployeeById(Integer id) 
	    {
	        Optional<User> user = userReponsitory.findById(id);
	        userReponsitory.deleteById(id);

	    }
}
