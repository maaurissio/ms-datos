package com.company.msdatos.resource;

import com.company.msdatos.dto.FuenteDatoRequest;
import com.company.msdatos.dto.FuenteDatoResponse;
import com.company.msdatos.service.FuenteDatoService;
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

@Path("/api/fuentes-datos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FuenteDatoResource {
    @Inject
    FuenteDatoService fuenteDatoService;

    @GET
    public List<FuenteDatoResponse> listAll() {
        return fuenteDatoService.listAll();
    }

    @GET
    @Path("/{id}")
    public FuenteDatoResponse getById(@PathParam("id") UUID id) {
        return fuenteDatoService.getById(id);
    }

    @POST
    public Response create(@Valid FuenteDatoRequest request) {
        return Response.status(Response.Status.CREATED).entity(fuenteDatoService.create(request)).build();
    }

    @PUT
    @Path("/{id}")
    public FuenteDatoResponse update(@PathParam("id") UUID id, @Valid FuenteDatoRequest request) {
        return fuenteDatoService.update(id, request);
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") UUID id) {
        fuenteDatoService.delete(id);
        return Response.noContent().build();
    }
}
