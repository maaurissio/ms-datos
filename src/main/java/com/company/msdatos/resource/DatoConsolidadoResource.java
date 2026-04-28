package com.company.msdatos.resource;

import com.company.msdatos.dto.DatoConsolidadoRequest;
import com.company.msdatos.dto.DatoConsolidadoResponse;
import com.company.msdatos.service.DatoConsolidadoService;
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

@Path("/api/datos-consolidados")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DatoConsolidadoResource {
    @Inject
    DatoConsolidadoService datoConsolidadoService;

    @GET
    public List<DatoConsolidadoResponse> listAll() {
        return datoConsolidadoService.listAll();
    }

    @GET
    @Path("/{id}")
    public DatoConsolidadoResponse getById(@PathParam("id") UUID id) {
        return datoConsolidadoService.getById(id);
    }

    @POST
    public Response create(@Valid DatoConsolidadoRequest request) {
        return Response.status(Response.Status.CREATED).entity(datoConsolidadoService.create(request)).build();
    }

    @PUT
    @Path("/{id}")
    public DatoConsolidadoResponse update(@PathParam("id") UUID id, @Valid DatoConsolidadoRequest request) {
        return datoConsolidadoService.update(id, request);
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") UUID id) {
        datoConsolidadoService.delete(id);
        return Response.noContent().build();
    }
}
