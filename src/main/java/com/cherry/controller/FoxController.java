package com.cherry.controller;

import com.cherry.model.entity.Fox;
import com.cherry.service.FoxServiceEjbLocal;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/fox")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FoxController {

    @EJB
    private FoxServiceEjbLocal foxServiceEjb;

    @GET
    @Path("/all")
    public List<Fox> getAllFoxes() {
        return foxServiceEjb.getAllFoxes();
    }
}
