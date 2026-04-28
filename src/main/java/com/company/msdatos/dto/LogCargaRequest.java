package com.company.msdatos.dto;

import com.company.msdatos.enums.EstadoCarga;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.UUID;

public record LogCargaRequest(
        @NotNull UUID datoConsolidadoId,
        @NotNull EstadoCarga estado,
        @NotBlank @Size(max = 500) String mensaje,
        LocalDateTime fecha
) {
}
