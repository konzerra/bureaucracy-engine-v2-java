package com.konzerra.bureaucracy_enginev2_java.domain.collection;


import com.konzerra.bureaucracy_enginev2_java.domain.collection.dto.CollectionSaveDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface CollectionService {
    // Save a new Collection
    Mono<Collection> save(CollectionSaveDto saveDto);

    // Update an existing Collection
    Mono<Collection> update(Collection collection);

    // Delete a Collection by ID
    Mono<Void> deleteById(String id);

    // Find a Collection by ID
    Mono<Collection> findById(String id);

    // Find all Collections
    Flux<Collection> findAll();

    // Find all Collections with pagination
    Flux<Collection> findAll(int pageSize, int pageNumber);
}
