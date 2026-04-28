package com.company.msdatos.entity;

import com.company.msdatos.enums.EstadoCarga;
import com.company.msdatos.enums.TipoDato;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.type.SqlTypes;

@Entity
@Table(name = "dato_consolidado")
public class DatoConsolidado {
    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "fuente_dato_id", nullable = false)
    private FuenteDato fuenteDato;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "sucursal_id", nullable = false)
    private Sucursal sucursal;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_dato", nullable = false, length = 30)
    private TipoDato tipoDato;

    @Column(nullable = false)
    private LocalDate periodo;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb", nullable = false)
    private JsonNode payload;

    @Column(name = "fecha_consolidacion", nullable = false)
    private LocalDateTime fechaConsolidacion;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private EstadoCarga estado;

    public void validar() {
        if (fuenteDato == null || sucursal == null || tipoDato == null || periodo == null || payload == null) {
            throw new IllegalStateException("Datos incompletos para consolidar");
        }
    }

    public void consolidar() {
        validar();
        this.fechaConsolidacion = LocalDateTime.now();
        this.estado = EstadoCarga.PROCESADO;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public FuenteDato getFuenteDato() {
        return fuenteDato;
    }

    public void setFuenteDato(FuenteDato fuenteDato) {
        this.fuenteDato = fuenteDato;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public TipoDato getTipoDato() {
        return tipoDato;
    }

    public void setTipoDato(TipoDato tipoDato) {
        this.tipoDato = tipoDato;
    }

    public LocalDate getPeriodo() {
        return periodo;
    }

    public void setPeriodo(LocalDate periodo) {
        this.periodo = periodo;
    }

    public JsonNode getPayload() {
        return payload;
    }

    public void setPayload(JsonNode payload) {
        this.payload = payload;
    }

    public LocalDateTime getFechaConsolidacion() {
        return fechaConsolidacion;
    }

    public void setFechaConsolidacion(LocalDateTime fechaConsolidacion) {
        this.fechaConsolidacion = fechaConsolidacion;
    }

    public EstadoCarga getEstado() {
        return estado;
    }

    public void setEstado(EstadoCarga estado) {
        this.estado = estado;
    }
}
