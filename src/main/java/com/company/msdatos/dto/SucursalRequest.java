package com.company.msdatos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SucursalRequest(
        @NotBlank @Size(max = 40) String codigo,
        @NotBlank @Size(max = 120) String nombre,
        @NotBlank @Size(max = 120) String ciudad,
        Boolean activo
) {
}
