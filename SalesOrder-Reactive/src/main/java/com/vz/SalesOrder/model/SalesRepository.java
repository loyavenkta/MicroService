package com.vz.SalesOrder.model;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface SalesRepository extends R2dbcRepository<SalesEntity, Long> {
}