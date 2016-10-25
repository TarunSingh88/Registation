package com.example.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

@Component
@Path("/home")
public class HomeController {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getHomePage() {
        return "Home Page";
    }

}