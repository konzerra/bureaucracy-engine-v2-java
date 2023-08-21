package com.konzerra.bureaucracy_enginev2_java.domain.collection.impl;

import com.konzerra.bureaucracy_enginev2_java.domain.auth.AuthUtil;
import com.konzerra.bureaucracy_enginev2_java.domain.collection.Collection;
import com.konzerra.bureaucracy_enginev2_java.domain.collection.CollectionMapper;
import com.konzerra.bureaucracy_enginev2_java.domain.collection.CollectionRepository;
import com.konzerra.bureaucracy_enginev2_java.domain.collection.dto.CollectionSaveInput;
import com.konzerra.bureaucracy_enginev2_java.domain.collection.CollectionService;
import com.konzerra.bureaucracy_enginev2_java.domain.collection.dto.CollectionUpdateInput;
import com.konzerra.bureaucracy_enginev2_java.exception.ApiException;
import com.konzerra.bureaucracy_enginev2_java.exception.ErrorCode;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CollectionServiceImpl implements CollectionService {

    private final CollectionRepository repository;
    private final AuthUtil authUtil;

    private final CollectionMapper mapper;

    public CollectionServiceImpl(CollectionRepository repository, AuthUtil authUtil, CollectionMapper mapper) {
        this.repository = repository;
        this.authUtil = authUtil;
        this.mapper = mapper;
    }

    @Override
    public Mono<Collection> save(CollectionSaveInput saveInput) {
        return authUtil.getUserName()
                .flatMap(userName ->
                        mapper.toModel(saveInput, userName).flatMap(repository::save)
                );
    }



    @Override
    public Mono<Collection> update(CollectionUpdateInput updateInput) {
        return repository.findById(updateInput.id())
                .flatMap(collection -> authUtil.isCurrentUser(collection.getOwner())
                .then(mapper.toUpdatedModel(collection, updateInput)
                        .flatMap(repository::save)
                        .switchIfEmpty(Mono.error(new ApiException("could not update", ErrorCode.UPDATE_FAILED)))
                ));
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return repository.findById(id)
                .flatMap(collection -> authUtil.isCurrentUser(collection.getOwner())
                .then(repository.deleteById(id)));
    }

    @Override
    public Mono<Collection> findById(String id) {
        return repository.findById(id)
                .flatMap(collection -> authUtil.isCurrentUser(collection.getOwner())
                        .then(Mono.just(collection))
                );
    }

    @Override
    public Flux<Collection> findAll() {
        return authUtil.getUserName()
                .flatMapMany(repository::findAllByOwner);
    }

    @Override
    public Flux<Collection> findAll(int pageSize, int pageNumber) {
        // Validate and adjust page size to a sensible default value
        int adjustedPageSize = pageSize <= 0 ? 20 : pageSize;
        // Adjust the page number to zero-based index
        int adjustedPageNumber = pageNumber <= 0 ? 0 : pageNumber - 1;

        // Construct a new Pageable object with adjusted values

        return authUtil.getUserName().flatMapMany( userName->
                repository.findAllByOwner(userName)
                        .skip((long) adjustedPageNumber * adjustedPageSize)
                        .take(pageSize));

    }


}
