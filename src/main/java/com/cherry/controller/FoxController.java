package com.cherry.controller;

import com.cherry.model.request.CreateFoxRequest;
import com.cherry.model.entity.Fox;
import com.cherry.service.FoxServiceEjbLocal;
import io.swagger.annotations.*;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/fox")
@Api(value = "Foxes")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FoxController {

    @EJB
    private FoxServiceEjbLocal foxServiceEjb;

    @GET
    @Path("/all")
    @ApiOperation(value = "Get all foxes")
    @ApiResponses({
            @ApiResponse(code = 200, message = "User found")
    })
    public List<Fox> getAllFoxes() {
        return foxServiceEjb.getAllFoxes();
    }

    @GET
    @Path("/{id}")
    @ApiOperation(value = "Get fox by ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Fox found"),
            @ApiResponse(code = 404, message = "Fox not found")
    })
    public Fox getFoxById(@PathParam("id") final int id) {
        return foxServiceEjb.getFoxById(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Create fox")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Fox created"),
            @ApiResponse(code = 400, message = "Invalid fox data")
    })
    public Response createFox(@Valid final CreateFoxRequest request) {
        Fox created = foxServiceEjb.createFox(request);
        return Response.status(Response.Status.CREATED)
                .entity(created)
                .build();
    }

    @DELETE
    @Path("/{id}")
    @ApiOperation(value = "Delete fox by ID")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Fox deleted"),
            @ApiResponse(code = 404, message = "Fox not found")
    })
    public Response deleteFoxById(@PathParam("id") final int id) {
        foxServiceEjb.deleteFoxById(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
