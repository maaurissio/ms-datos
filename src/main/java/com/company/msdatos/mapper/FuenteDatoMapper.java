package com.company.msdatos.mapper;

import com.company.msdatos.dto.FuenteDatoRequest;
import com.company.msdatos.dto.FuenteDatoResponse;
import com.company.msdatos.entity.FuenteDato;

public final class FuenteDatoMapper {
    private FuenteDatoMapper() {
    }

    public static FuenteDato toEntity(FuenteDatoRequest request) {
        FuenteDato entity = new FuenteDato();
        entity.setNombre(request.nombre());
        entity.setSistemaOrigen(request.sistemaOrigen());
        entity.setDescripcion(request.descripcion());
        if (request.activo() != null) {
            entity.setActivo(request.activo());
        }
        return entity;
    }

    public static FuenteDatoResponse toResponse(FuenteDato entity) {
        return new FuenteDatoResponse(
                entity.getId(),
                entity.getNombre(),
                entity.getSistemaOrigen(),
                entity.getDescripcion(),
                entity.isActivo()
        );
    }
}
