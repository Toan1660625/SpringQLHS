package com.example.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.service.UserDetailsServiceImpl;

/*
 * Copyright (C) 2019 by GMO Runsystem Company
 * Create WebSecurityConfig  class
 * @version 1.0
 * @author ToanLM
 */

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	

//	
//	    @Autowired
//	    private DataSource dataSource;
	
		@Autowired
	    private UserDetailsServiceImpl userDetailsService;
		
		
	    @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
	    
//		@Bean
//		public DaoAuthenticationProvider authenticationProvider() {
//			DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//			authProvider.setUserDetailsService(userDetailsService);
//			authProvider.setPasswordEncoder(passwordEncoder());
//			return authProvider;
//		}
	    
	    @Autowired
	    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	    }
	    
//	    @Override
//	    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
//	        auth.userDetailsService(userDetailsService)
//	                .passwordEncoder(passwordEncoder())
//	                .and()
//	                .authenticationProvider(authenticationProvider())
//	                .jdbcAuthentication()
//	                .dataSource(dataSource);
//	    }
	    
	    
	    @Override
	    protected void configure(HttpSecurity http) throws Exception {
	    	System.out.println("kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk22222222");
			System.out.println("kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk222222222");
	        http
	            .authorizeRequests()
	                .antMatchers("/register","/login","/loginSuccess","/","/index").permitAll()
	                .antMatchers("/delete/{studentId}","/edit/{infoId}","/add").hasRole("ADMIN")
//	                .anyRequest().authenticated()
	                .and()
	            .formLogin()
	                .loginPage("/login")
	                .usernameParameter("userName")
	                .passwordParameter("password")
//	    			.loginProcessingUrl("/index").permitAll()
	                .defaultSuccessUrl("/index").permitAll()   // Sẽ chạy sang GET
	                .failureUrl("/login?error")
	                .and()
	             .logout()
//	             	.invalidateHttpSession(true)
//	             	.clearAuthentication(true)
	                .logoutSuccessUrl("/logout").permitAll()  
//	                .deleteCookies("JSESSIONID")
                    .permitAll() 
                    .and()
                 .exceptionHandling()
                    .accessDeniedPage("/403");
	        
	      
       
	    }

}
