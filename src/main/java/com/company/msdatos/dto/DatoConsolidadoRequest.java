package com.company.msdatos.dto;

import com.company.msdatos.enums.EstadoCarga;
import com.company.msdatos.enums.TipoDato;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.UUID;

public record DatoConsolidadoRequest(
        @NotNull UUID fuenteDatoId,
        @NotNull UUID sucursalId,
        @NotNull TipoDato tipoDato,
        @NotNull LocalDate periodo,
        @NotNull JsonNode payload,
        EstadoCarga estado
) {
}
