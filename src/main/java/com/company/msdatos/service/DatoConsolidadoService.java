package com.company.msdatos.service;

import com.company.msdatos.dto.DatoConsolidadoRequest;
import com.company.msdatos.dto.DatoConsolidadoResponse;
import com.company.msdatos.entity.DatoConsolidado;
import com.company.msdatos.entity.FuenteDato;
import com.company.msdatos.entity.Sucursal;
import com.company.msdatos.enums.EstadoCarga;
import com.company.msdatos.exception.NotFoundException;
import com.company.msdatos.mapper.DatoConsolidadoMapper;
import com.company.msdatos.repository.DatoConsolidadoRepository;
import com.company.msdatos.repository.FuenteDatoRepository;
import com.company.msdatos.repository.SucursalRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class DatoConsolidadoService {
    @Inject
    DatoConsolidadoRepository datoConsolidadoRepository;

    @Inject
    FuenteDatoRepository fuenteDatoRepository;

    @Inject
    SucursalRepository sucursalRepository;

    public List<DatoConsolidadoResponse> listAll() {
        return datoConsolidadoRepository.listAll()
                .stream()
                .map(DatoConsolidadoMapper::toResponse)
                .toList();
    }

    public DatoConsolidadoResponse getById(UUID id) {
        return DatoConsolidadoMapper.toResponse(getEntity(id));
    }

    @Transactional
    public DatoConsolidadoResponse create(DatoConsolidadoRequest request) {
        DatoConsolidado entity = new DatoConsolidado();
        entity.setFuenteDato(getFuente(request.fuenteDatoId()));
        entity.setSucursal(getSucursal(request.sucursalId()));
        entity.setTipoDato(request.tipoDato());
        entity.setPeriodo(request.periodo());
        entity.setPayload(request.payload());
        entity.setEstado(request.estado() != null ? request.estado() : EstadoCarga.PENDIENTE);
        entity.setFechaConsolidacion(LocalDateTime.now());
        entity.validar();
        datoConsolidadoRepository.persist(entity);
        return DatoConsolidadoMapper.toResponse(entity);
    }

    @Transactional
    public DatoConsolidadoResponse update(UUID id, DatoConsolidadoRequest request) {
        DatoConsolidado entity = getEntity(id);
        entity.setFuenteDato(getFuente(request.fuenteDatoId()));
        entity.setSucursal(getSucursal(request.sucursalId()));
        entity.setTipoDato(request.tipoDato());
        entity.setPeriodo(request.periodo());
        entity.setPayload(request.payload());
        if (request.estado() != null) {
            entity.setEstado(request.estado());
        }
        if (entity.getFechaConsolidacion() == null) {
            entity.setFechaConsolidacion(LocalDateTime.now());
        }
        entity.validar();
        return DatoConsolidadoMapper.toResponse(entity);
    }

    @Transactional
    public void delete(UUID id) {
        DatoConsolidado entity = getEntity(id);
        datoConsolidadoRepository.delete(entity);
    }

    private DatoConsolidado getEntity(UUID id) {
        DatoConsolidado entity = datoConsolidadoRepository.findById(id);
        if (entity == null) {
            throw new NotFoundException("DatoConsolidado not found: " + id);
        }
        return entity;
    }

    private FuenteDato getFuente(UUID id) {
        FuenteDato entity = fuenteDatoRepository.findById(id);
        if (entity == null) {
            throw new NotFoundException("FuenteDato not found: " + id);
        }
        return entity;
    }

    private Sucursal getSucursal(UUID id) {
        Sucursal entity = sucursalRepository.findById(id);
        if (entity == null) {
            throw new NotFoundException("Sucursal not found: " + id);
        }
        return entity;
    }
}
