package com.example.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.model.User;
import com.example.service.UserService;

@Service
public class CurrentUser {

	private final UserService userService;

	@Autowired
	public CurrentUser(UserService userService) {
		this.userService = userService;
	}

	public  User getCurrentUser(){
	System.out.println(SecurityContextHolder.getContext().getAuthentication().getClass());
	UsernamePasswordAuthenticationToken token=(UsernamePasswordAuthenticationToken)SecurityContextHolder.getContext().getAuthentication();
   
	System.out.println(token.getPrincipal());
	UserDetails s=(UserDetails)token.getPrincipal();
	System.out.println(s.getUsername() +" "+s.getAuthorities() );
	
	
	User user=userService.getUserByEmail(s.getUsername());
	
	return user;
	}
}
