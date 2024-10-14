package com.vz.ItemServiceReactive.model;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Mono;
@Repository
public interface ItemServiceRepository extends R2dbcRepository<ItemService, String> {

	Mono<ItemService> findById(Long id);

	void deleteById(Long id);

	Mono<ItemService> findByItemName(String itemName);
}
