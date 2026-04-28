package com.company.msdatos.repository;

import com.company.msdatos.entity.Sucursal;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.UUID;

@ApplicationScoped
public class SucursalRepository implements PanacheRepositoryBase<Sucursal, UUID> {
}
