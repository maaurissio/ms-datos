package com.company.msdatos.service;

import com.company.msdatos.dto.FuenteDatoRequest;
import com.company.msdatos.dto.FuenteDatoResponse;
import com.company.msdatos.entity.FuenteDato;
import com.company.msdatos.exception.NotFoundException;
import com.company.msdatos.mapper.FuenteDatoMapper;
import com.company.msdatos.repository.FuenteDatoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class FuenteDatoService {
    @Inject
    FuenteDatoRepository fuenteDatoRepository;

    public List<FuenteDatoResponse> listAll() {
        return fuenteDatoRepository.listAll()
                .stream()
                .map(FuenteDatoMapper::toResponse)
                .toList();
    }

    public FuenteDatoResponse getById(UUID id) {
        return FuenteDatoMapper.toResponse(getEntity(id));
    }

    @Transactional
    public FuenteDatoResponse create(FuenteDatoRequest request) {
        FuenteDato entity = FuenteDatoMapper.toEntity(request);
        fuenteDatoRepository.persist(entity);
        return FuenteDatoMapper.toResponse(entity);
    }

    @Transactional
    public FuenteDatoResponse update(UUID id, FuenteDatoRequest request) {
        FuenteDato entity = getEntity(id);
        entity.setNombre(request.nombre());
        entity.setSistemaOrigen(request.sistemaOrigen());
        entity.setDescripcion(request.descripcion());
        if (request.activo() != null) {
            entity.setActivo(request.activo());
        }
        return FuenteDatoMapper.toResponse(entity);
    }

    @Transactional
    public void delete(UUID id) {
        FuenteDato entity = getEntity(id);
        entity.desactivar();
    }

    private FuenteDato getEntity(UUID id) {
        FuenteDato entity = fuenteDatoRepository.findById(id);
        if (entity == null) {
            throw new NotFoundException("FuenteDato not found: " + id);
        }
        return entity;
    }
}
