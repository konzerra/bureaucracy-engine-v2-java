package com.konzerra.bureaucracy_enginev2_java.domain.collection;

import com.konzerra.bureaucracy_enginev2_java.domain.collection.dto.CollectionSaveInput;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
public class CollectionResolver {
    private final CollectionService collectionService;

    public CollectionResolver(CollectionService collectionService) {
        this.collectionService = collectionService;
    }

    @QueryMapping
    @PreAuthorize("hasAnyRole('ROLE_USER')")
    public Flux<Collection> collections() {
        return collectionService.findAll();
    }

    @QueryMapping
    @PreAuthorize("hasAnyRole('ROLE_USER')")
    public Flux<Collection> collectionsWithPagination(int pageSize, int pageNumber) {
        return collectionService.findAll(pageSize, pageNumber);
    }

    @MutationMapping
    @PreAuthorize("hasAnyRole('ROLE_USER')")
    public Mono<Collection> saveCollection(@Argument CollectionSaveInput saveInput){
        return collectionService.save(saveInput);
    }
}

