package com.example.config;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import com.example.controller.HomeController;
import com.example.controller.UserController;
import com.example.controller.UsersController;


@Configuration
@ApplicationPath("/api/v1")
public class JerseyConfig extends ResourceConfig{
	
	public JerseyConfig(){		
		register(HomeController.class);
		register(UserController.class);
		register(UsersController.class);
	}

}
