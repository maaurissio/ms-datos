package com.company.msdatos.repository;

import com.company.msdatos.entity.FuenteDato;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.UUID;

@ApplicationScoped
public class FuenteDatoRepository implements PanacheRepositoryBase<FuenteDato, UUID> {
}
