package com.konzerra.bureaucracy_enginev2_java.domain.collection.impl;

import com.konzerra.bureaucracy_enginev2_java.domain.collection.Collection;
import com.konzerra.bureaucracy_enginev2_java.domain.collection.CollectionMapper;
import com.konzerra.bureaucracy_enginev2_java.domain.collection.dto.CollectionSaveInput;
import com.konzerra.bureaucracy_enginev2_java.domain.collection.dto.CollectionUpdateInput;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class CollectionMapperImpl implements CollectionMapper {
    @Override
    public Mono<Collection> toModel(CollectionUpdateInput updateInput) {
        return Mono.just(Collection.builder()
                        .id(updateInput.id())
                        .description(updateInput.description())
                        .name(updateInput.name())
                .build());
    }

    @Override
    public Mono<Collection> toModel(CollectionSaveInput saveInput) {
        return Mono.just(Collection.builder()
                .description(saveInput.description())
                .name(saveInput.name())
                .build());
    }
}
