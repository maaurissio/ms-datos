package com.company.msdatos.exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<Exception> {
    @Override
    public Response toResponse(Exception exception) {
        ErrorResponse payload = new ErrorResponse("UNEXPECTED_ERROR", "Unexpected error");
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(payload).build();
    }
}
