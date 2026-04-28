package com.company.msdatos.dto;

import java.util.UUID;

public record SucursalResponse(
        UUID id,
        String codigo,
        String nombre,
        String ciudad,
        boolean activo
) {
}
