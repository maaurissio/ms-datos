package com.company.msdatos.repository;

import com.company.msdatos.entity.LogCarga;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.UUID;

@ApplicationScoped
public class LogCargaRepository implements PanacheRepositoryBase<LogCarga, UUID> {
}
