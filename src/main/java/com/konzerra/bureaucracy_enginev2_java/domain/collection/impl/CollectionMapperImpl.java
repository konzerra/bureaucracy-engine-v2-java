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
    public Mono<Collection> toUpdatedModel(Collection collection, CollectionUpdateInput updateInput) {

        collection.setName(updateInput.name());
        collection.setDescription(updateInput.description());
        return Mono.just(collection);
    }

    @Override
    public Mono<Collection> toModel(CollectionSaveInput saveInput, String userName) {
        return Mono.just(Collection.builder()
                .description(saveInput.description())
                .name(saveInput.name())
                .owner(userName)
                .build());
    }
}
