package com.company.msdatos.resource;

import com.company.msdatos.dto.SucursalRequest;
import com.company.msdatos.dto.SucursalResponse;
import com.company.msdatos.service.SucursalService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import java.util.UUID;

@Path("/api/sucursales")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SucursalResource {
    @Inject
    SucursalService sucursalService;

    @GET
    public List<SucursalResponse> listAll() {
        return sucursalService.listAll();
    }

    @GET
    @Path("/{id}")
    public SucursalResponse getById(@PathParam("id") UUID id) {
        return sucursalService.getById(id);
    }

    @POST
    public Response create(@Valid SucursalRequest request) {
        return Response.status(Response.Status.CREATED).entity(sucursalService.create(request)).build();
    }

    @PUT
    @Path("/{id}")
    public SucursalResponse update(@PathParam("id") UUID id, @Valid SucursalRequest request) {
        return sucursalService.update(id, request);
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") UUID id) {
        sucursalService.delete(id);
        return Response.noContent().build();
    }
}
