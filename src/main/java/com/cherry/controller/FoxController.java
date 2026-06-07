package com.cherry.controller;

import com.cherry.model.request.CreateFoxRequest;
import com.cherry.model.entity.Fox;
import com.cherry.service.FoxServiceEjbLocal;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/fox")
@RequestScoped
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

    @GET
    @Path("/{id}")
    public Fox getFoxById(@PathParam("id") final int id) {
        return foxServiceEjb.getFoxById(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createFox(@Valid final CreateFoxRequest request) {
        Fox created = foxServiceEjb.createFox(request);
        return Response.status(Response.Status.CREATED)
                .entity(created)
                .build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteFoxById(@PathParam("id") final int id) {
        foxServiceEjb.deleteFoxById(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
