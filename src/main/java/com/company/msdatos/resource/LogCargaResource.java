package com.company.msdatos.resource;

import com.company.msdatos.dto.LogCargaRequest;
import com.company.msdatos.dto.LogCargaResponse;
import com.company.msdatos.service.LogCargaService;
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

@Path("/api/logs-carga")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LogCargaResource {
    @Inject
    LogCargaService logCargaService;

    @GET
    public List<LogCargaResponse> listAll() {
        return logCargaService.listAll();
    }

    @GET
    @Path("/{id}")
    public LogCargaResponse getById(@PathParam("id") UUID id) {
        return logCargaService.getById(id);
    }

    @POST
    public Response create(@Valid LogCargaRequest request) {
        return Response.status(Response.Status.CREATED).entity(logCargaService.create(request)).build();
    }

    @PUT
    @Path("/{id}")
    public LogCargaResponse update(@PathParam("id") UUID id, @Valid LogCargaRequest request) {
        return logCargaService.update(id, request);
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") UUID id) {
        logCargaService.delete(id);
        return Response.noContent().build();
    }
}
