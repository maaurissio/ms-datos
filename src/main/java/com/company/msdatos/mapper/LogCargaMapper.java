package com.company.msdatos.mapper;

import com.company.msdatos.dto.LogCargaResponse;
import com.company.msdatos.entity.LogCarga;

public final class LogCargaMapper {
    private LogCargaMapper() {
    }

    public static LogCargaResponse toResponse(LogCarga entity) {
        return new LogCargaResponse(
                entity.getId(),
                entity.getDatoConsolidado().getId(),
                entity.getEstado(),
                entity.getMensaje(),
                entity.getFecha()
        );
    }
}
