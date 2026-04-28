package com.company.msdatos.service;

import com.company.msdatos.dto.SucursalRequest;
import com.company.msdatos.dto.SucursalResponse;
import com.company.msdatos.entity.Sucursal;
import com.company.msdatos.exception.NotFoundException;
import com.company.msdatos.mapper.SucursalMapper;
import com.company.msdatos.repository.SucursalRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class SucursalService {
    @Inject
    SucursalRepository sucursalRepository;

    public List<SucursalResponse> listAll() {
        return sucursalRepository.listAll()
                .stream()
                .map(SucursalMapper::toResponse)
                .toList();
    }

    public SucursalResponse getById(UUID id) {
        return SucursalMapper.toResponse(getEntity(id));
    }

    @Transactional
    public SucursalResponse create(SucursalRequest request) {
        Sucursal entity = SucursalMapper.toEntity(request);
        sucursalRepository.persist(entity);
        return SucursalMapper.toResponse(entity);
    }

    @Transactional
    public SucursalResponse update(UUID id, SucursalRequest request) {
        Sucursal entity = getEntity(id);
        entity.setCodigo(request.codigo());
        entity.setNombre(request.nombre());
        entity.setCiudad(request.ciudad());
        if (request.activo() != null) {
            entity.setActivo(request.activo());
        }
        return SucursalMapper.toResponse(entity);
    }

    @Transactional
    public void delete(UUID id) {
        Sucursal entity = getEntity(id);
        entity.desactivar();
    }

    private Sucursal getEntity(UUID id) {
        Sucursal entity = sucursalRepository.findById(id);
        if (entity == null) {
            throw new NotFoundException("Sucursal not found: " + id);
        }
        return entity;
    }
}
