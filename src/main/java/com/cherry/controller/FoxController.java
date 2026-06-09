package com.cherry.controller;

import com.cherry.model.request.CreateFoxRequest;
import com.cherry.model.entity.Fox;
import com.cherry.service.FoxServiceEjbLocal;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

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
@Tag(name = "Fox", description = "Operations related to fox management")
public class FoxController {

    @EJB
    private FoxServiceEjbLocal foxServiceEjb;

    @GET
    @Path("/all")
    @Operation(summary = "Get all foxes", tags = {"Fox"})
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "List of foxes",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Fox.class))
            )
    })
    public List<Fox> getAllFoxes() {
        return foxServiceEjb.getAllFoxes();
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Get fox by ID", tags = {"Fox"})
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Fox found",
                    content = @Content(schema = @Schema(implementation = Fox.class))
            ),
            @ApiResponse(responseCode = "404", description = "Fox not found")
    })
    public Fox getFoxById(@PathParam("id") final int id) {
        return foxServiceEjb.getFoxById(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Create a new fox", tags = {"Fox"})
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Fox created",
                    content = @Content(schema = @Schema(implementation = Fox.class))
            ),
            @ApiResponse(responseCode = "400", description = "Invalid fox data")
    })
    public Response createFox(
            @Valid
            @RequestBody(description = "Fox creation request", required = true,
                    content = @Content(schema = @Schema(implementation = CreateFoxRequest.class)))
            final CreateFoxRequest request)
    {
        Fox created = foxServiceEjb.createFox(request);
        return Response.status(Response.Status.CREATED)
                .entity(created)
                .build();
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Delete fox by ID", tags = {"Fox"})
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Fox deleted"),
            @ApiResponse(responseCode = "404", description = "Fox not found")
    })
    public Response deleteFoxById(@PathParam("id") final int id) {
        foxServiceEjb.deleteFoxById(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
