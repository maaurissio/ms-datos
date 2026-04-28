package com.company.msdatos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record FuenteDatoRequest(
        @NotBlank @Size(max = 120) String nombre,
        @NotBlank @Size(max = 120) String sistemaOrigen,
        @Size(max = 400) String descripcion,
        Boolean activo
) {
}
