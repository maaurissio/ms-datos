package com.company.msdatos.mapper;

import com.company.msdatos.dto.DatoConsolidadoResponse;
import com.company.msdatos.entity.DatoConsolidado;

public final class DatoConsolidadoMapper {
    private DatoConsolidadoMapper() {
    }

    public static DatoConsolidadoResponse toResponse(DatoConsolidado entity) {
        return new DatoConsolidadoResponse(
                entity.getId(),
                entity.getFuenteDato().getId(),
                entity.getSucursal().getId(),
                entity.getTipoDato(),
                entity.getPeriodo(),
                entity.getPayload(),
                entity.getFechaConsolidacion(),
                entity.getEstado()
        );
    }
}
