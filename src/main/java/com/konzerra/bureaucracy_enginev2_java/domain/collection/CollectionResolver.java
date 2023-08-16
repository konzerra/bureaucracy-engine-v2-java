package com.konzerra.bureaucracy_enginev2_java.domain.collection;

import com.konzerra.bureaucracy_enginev2_java.domain.collection.dto.CollectionSaveDto;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller("/collection")
public class CollectionResolver {
    private final CollectionService collectionService;

    public CollectionResolver(CollectionService collectionService) {
        this.collectionService = collectionService;
    }

    @QueryMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public Flux<Collection> collections() {
        return collectionService.findAll();
    }

    @QueryMapping
    public Flux<Collection> collectionsWithPagination(int pageSize, int pageNumber) {
        return collectionService.findAll(pageSize, pageNumber);
    }

    @MutationMapping
    public Mono<Collection> saveCollection(@Argument CollectionSaveDto saveInput){
        return collectionService.save(saveInput);
    }
}

