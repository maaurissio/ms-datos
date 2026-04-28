package com.company.msdatos.mapper;

import com.company.msdatos.dto.SucursalRequest;
import com.company.msdatos.dto.SucursalResponse;
import com.company.msdatos.entity.Sucursal;

public final class SucursalMapper {
    private SucursalMapper() {
    }

    public static Sucursal toEntity(SucursalRequest request) {
        Sucursal entity = new Sucursal();
        entity.setCodigo(request.codigo());
        entity.setNombre(request.nombre());
        entity.setCiudad(request.ciudad());
        if (request.activo() != null) {
            entity.setActivo(request.activo());
        }
        return entity;
    }

    public static SucursalResponse toResponse(Sucursal entity) {
        return new SucursalResponse(
                entity.getId(),
                entity.getCodigo(),
                entity.getNombre(),
                entity.getCiudad(),
                entity.isActivo()
        );
    }
}
