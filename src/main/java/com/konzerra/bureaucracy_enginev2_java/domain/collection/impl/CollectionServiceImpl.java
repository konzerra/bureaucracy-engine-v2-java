package com.konzerra.bureaucracy_enginev2_java.domain.collection.impl;

import com.konzerra.bureaucracy_enginev2_java.domain.auth.AuthUtil;
import com.konzerra.bureaucracy_enginev2_java.domain.collection.Collection;
import com.konzerra.bureaucracy_enginev2_java.domain.collection.CollectionRepository;
import com.konzerra.bureaucracy_enginev2_java.domain.collection.dto.CollectionSaveDto;
import com.konzerra.bureaucracy_enginev2_java.domain.collection.CollectionService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collections;

@Service
public class CollectionServiceImpl implements CollectionService {

    private final CollectionRepository collectionRepository;
    private final AuthUtil authUtil;

    public CollectionServiceImpl(CollectionRepository collectionRepository, AuthUtil authUtil) {
        this.collectionRepository = collectionRepository;
        this.authUtil = authUtil;
    }

    @Override
    public Mono<Collection> save(CollectionSaveDto saveDto) {
        return authUtil.getUserName()
                .flatMap(userName -> collectionRepository.save(new Collection(
                        null,
                        saveDto.name(),
                        saveDto.description(),
                        userName,
                        Collections.emptyList()
                )));
    }



    @Override
    public Mono<Collection> update(Collection collection) {



        return collectionRepository.save(collection);
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return collectionRepository.deleteById(id);
    }

    @Override
    public Mono<Collection> findById(String id) {
        return collectionRepository.findById(id);
    }

    @Override
    public Flux<Collection> findAll() {
        return authUtil.getUserName()
                .flatMapMany(collectionRepository::findAllByOwner);
    }

    @Override
    public Flux<Collection> findAll(int pageSize, int pageNumber) {
        // Validate and adjust page size to a sensible default value
        int adjustedPageSize = pageSize <= 0 ? 20 : pageSize;
        // Adjust the page number to zero-based index
        int adjustedPageNumber = pageNumber <= 0 ? 0 : pageNumber - 1;

        // Construct a new Pageable object with adjusted values
        PageRequest pageable = PageRequest.of(adjustedPageNumber, adjustedPageSize);

        return authUtil.getUserName().flatMapMany( userName->
                collectionRepository.findAllByOwner(userName)
                        .skip((long) pageNumber * pageSize)
                        .take(pageSize));

    }


}
