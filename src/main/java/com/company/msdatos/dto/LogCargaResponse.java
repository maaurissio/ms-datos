package com.company.msdatos.dto;

import com.company.msdatos.enums.EstadoCarga;
import java.time.LocalDateTime;
import java.util.UUID;

public record LogCargaResponse(
        UUID id,
        UUID datoConsolidadoId,
        EstadoCarga estado,
        String mensaje,
        LocalDateTime fecha
) {
}
