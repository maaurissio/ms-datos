package com.company.msdatos.repository;

import com.company.msdatos.entity.DatoConsolidado;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.UUID;

@ApplicationScoped
public class DatoConsolidadoRepository implements PanacheRepositoryBase<DatoConsolidado, UUID> {
}
