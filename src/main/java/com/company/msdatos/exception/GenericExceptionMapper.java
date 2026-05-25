package com.company.msdatos.exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import java.util.logging.Level;
import java.util.logging.Logger;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<Exception> {
    private static final Logger LOG = Logger.getLogger(GenericExceptionMapper.class.getName());

    @Override
    public Response toResponse(Exception exception) {
        LOG.log(Level.SEVERE, "UNEXPECTED_ERROR", exception);
        ErrorResponse payload = new ErrorResponse("UNEXPECTED_ERROR", exception.getMessage() != null ? exception.getMessage() : "Unexpected error");
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(payload).build();
    }
}
