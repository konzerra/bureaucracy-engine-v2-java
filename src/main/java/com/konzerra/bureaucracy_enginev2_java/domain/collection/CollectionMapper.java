package com.konzerra.bureaucracy_enginev2_java.domain.collection;

import com.konzerra.bureaucracy_enginev2_java.domain.collection.dto.CollectionSaveInput;
import com.konzerra.bureaucracy_enginev2_java.domain.collection.dto.CollectionUpdateInput;
import reactor.core.publisher.Mono;


public interface CollectionMapper {
    Mono<Collection> toUpdatedModel(Collection collection, CollectionUpdateInput updateInput);

    Mono<Collection> toModel(CollectionSaveInput saveInput, String userName);

}
