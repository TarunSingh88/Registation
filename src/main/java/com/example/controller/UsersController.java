package com.example.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import com.example.model.User;
import com.example.service.UserService;
import com.example.utils.CurrentUser;

@Component
@Path("/users")
public class UsersController {
	   private final UserService userService;

	    @Autowired
	    public UsersController(UserService userService) {
	        this.userService = userService;
	    }
        @Autowired
        private CurrentUser currentUser;
	    
	    @GET	    
	    @Produces(MediaType.APPLICATION_JSON)
	    @PreAuthorize("hasAuthority('ADMIN')")
	    public Response getUsersPage() {
	    	User user=currentUser.getCurrentUser();
	    	System.out.println(user.getEmail() +"   "+user.getRole());
	    	return Response.ok().entity(userService.getAllUsers()).build();
	    }

	}