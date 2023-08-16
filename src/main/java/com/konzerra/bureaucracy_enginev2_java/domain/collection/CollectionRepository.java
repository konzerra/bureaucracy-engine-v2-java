package com.konzerra.bureaucracy_enginev2_java.domain.collection;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.awt.print.Pageable;

@Repository
public interface CollectionRepository extends ReactiveMongoRepository<Collection, String> {
    Flux<Collection> findAllByOwner(String owner);
}
