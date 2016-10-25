package com.example.controller;

import java.util.NoSuchElementException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.dto.UserCreateForm;
import com.example.service.UserService;

@Component
@Path("/user")
public class UserController {

    private final UserService userService;
 
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
       
    }

    /*@InitBinder("form")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(userCreateFormValidator);
    }*/

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserPage(@PathVariable Long id) {
    	
        return Response.ok().entity(userService.getUserById(id)
                .orElseThrow(() -> new NoSuchElementException(String.format("User=%s not found", id)))).build();
    }

    @Path("/create")
    @GET  
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserCreatePage() {
        return Response.ok().entity(new UserCreateForm()).build();
    }

    @Path("/create")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response handleUserCreateForm(UserCreateForm form) {
      
        try {
            userService.create(form);
        } catch (DataIntegrityViolationException e) {
            return Response.status(Status.BAD_REQUEST).entity("User with Email alreday exist").build();
        }
        return Response.ok().build();
    }

}