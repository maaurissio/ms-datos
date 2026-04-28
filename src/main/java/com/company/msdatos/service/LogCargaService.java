package com.company.msdatos.service;

import com.company.msdatos.dto.LogCargaRequest;
import com.company.msdatos.dto.LogCargaResponse;
import com.company.msdatos.entity.DatoConsolidado;
import com.company.msdatos.entity.LogCarga;
import com.company.msdatos.exception.NotFoundException;
import com.company.msdatos.mapper.LogCargaMapper;
import com.company.msdatos.repository.DatoConsolidadoRepository;
import com.company.msdatos.repository.LogCargaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class LogCargaService {
    @Inject
    LogCargaRepository logCargaRepository;

    @Inject
    DatoConsolidadoRepository datoConsolidadoRepository;

    public List<LogCargaResponse> listAll() {
        return logCargaRepository.listAll()
                .stream()
                .map(LogCargaMapper::toResponse)
                .toList();
    }

    public LogCargaResponse getById(UUID id) {
        return LogCargaMapper.toResponse(getEntity(id));
    }

    @Transactional
    public LogCargaResponse create(LogCargaRequest request) {
        LogCarga entity = new LogCarga();
        entity.setDatoConsolidado(getDatoConsolidado(request.datoConsolidadoId()));
        entity.setEstado(request.estado());
        entity.setMensaje(request.mensaje());
        entity.setFecha(request.fecha() != null ? request.fecha() : LocalDateTime.now());
        logCargaRepository.persist(entity);
        return LogCargaMapper.toResponse(entity);
    }

    @Transactional
    public LogCargaResponse update(UUID id, LogCargaRequest request) {
        LogCarga entity = getEntity(id);
        entity.setDatoConsolidado(getDatoConsolidado(request.datoConsolidadoId()));
        entity.setEstado(request.estado());
        entity.setMensaje(request.mensaje());
        if (request.fecha() != null) {
            entity.setFecha(request.fecha());
        }
        return LogCargaMapper.toResponse(entity);
    }

    @Transactional
    public void delete(UUID id) {
        LogCarga entity = getEntity(id);
        logCargaRepository.delete(entity);
    }

    private LogCarga getEntity(UUID id) {
        LogCarga entity = logCargaRepository.findById(id);
        if (entity == null) {
            throw new NotFoundException("LogCarga not found: " + id);
        }
        return entity;
    }

    private DatoConsolidado getDatoConsolidado(UUID id) {
        DatoConsolidado entity = datoConsolidadoRepository.findById(id);
        if (entity == null) {
            throw new NotFoundException("DatoConsolidado not found: " + id);
        }
        return entity;
    }
}
