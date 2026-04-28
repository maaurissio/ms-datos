package com.company.msdatos.dto;

import com.company.msdatos.enums.EstadoCarga;
import com.company.msdatos.enums.TipoDato;
import com.fasterxml.jackson.databind.JsonNode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record DatoConsolidadoResponse(
        UUID id,
        UUID fuenteDatoId,
        UUID sucursalId,
        TipoDato tipoDato,
        LocalDate periodo,
        JsonNode payload,
        LocalDateTime fechaConsolidacion,
        EstadoCarga estado
) {
}
