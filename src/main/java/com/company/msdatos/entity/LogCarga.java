package com.company.msdatos.entity;

import com.company.msdatos.enums.EstadoCarga;
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
import java.time.LocalDateTime;
import java.util.UUID;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Table(name = "log_carga")
public class LogCarga {
    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "dato_consolidado_id", nullable = false)
    private DatoConsolidado datoConsolidado;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private EstadoCarga estado;

    @Column(nullable = false, length = 500)
    private String mensaje;

    @Column(nullable = false)
    private LocalDateTime fecha;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public DatoConsolidado getDatoConsolidado() {
        return datoConsolidado;
    }

    public void setDatoConsolidado(DatoConsolidado datoConsolidado) {
        this.datoConsolidado = datoConsolidado;
    }

    public EstadoCarga getEstado() {
        return estado;
    }

    public void setEstado(EstadoCarga estado) {
        this.estado = estado;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
}
