package com.company.msdatos.dto;

import java.util.UUID;

public record FuenteDatoResponse(
        UUID id,
        String nombre,
        String sistemaOrigen,
        String descripcion,
        boolean activo
) {
}
