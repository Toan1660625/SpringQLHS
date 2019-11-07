package com.example.config;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.example.entity.Student;
import com.example.entity.StudentInfo;
import com.example.entity.User;
/*
 * Copyright (C) 2019 by GMO Runsystem Company
 * Create HibernateUtil  class
 * @version 1.0
 * @author ToanLM
 */

public class HibernateUtil {

	private static SessionFactory sessionFactory;
	
	 /**
	  * Scan and config hbm.xml
	  */
	private static SessionFactory buildSessionFactory() {
		try {
			// Create the SessionFactory from hibernate.cfg.xml
			Configuration configuration = new Configuration();
			configuration.configure("hibernate.cfg.xml");								//config to hibernate.cfg.xml
			configuration.addResource("named-queries.hbm.xml");							//config to named-queries.hbm.xml
			configuration.addAnnotatedClass(User.class);
			configuration.addAnnotatedClass(Student.class);
			configuration.addAnnotatedClass(StudentInfo.class);
			System.out.println("Hibernate Configuration loaded");

			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
					.applySettings(configuration.getProperties()).build();

			System.out.println("Hibernate serviceRegistry created");

			SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);

			return sessionFactory;
		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			ex.printStackTrace();
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null)
			sessionFactory = buildSessionFactory();
		return sessionFactory;
	}

}
