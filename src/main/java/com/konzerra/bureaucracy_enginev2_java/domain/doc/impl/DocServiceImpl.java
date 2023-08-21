package com.konzerra.bureaucracy_enginev2_java.domain.doc.impl;

import com.konzerra.bureaucracy_enginev2_java.domain.auth.AuthUtil;
import com.konzerra.bureaucracy_enginev2_java.domain.doc.*;
import com.konzerra.bureaucracy_enginev2_java.domain.doc.dto.DocSaveInput;
import com.konzerra.bureaucracy_enginev2_java.domain.doc.dto.DocUpdateInput;
import com.konzerra.bureaucracy_enginev2_java.exception.ApiException;
import com.konzerra.bureaucracy_enginev2_java.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class DocServiceImpl implements DocService {

    private final DocRepository repository;
    private final AuthUtil authUtil;

    private final DocMapper mapper;

    @Override
    public Mono<Doc> save(DocSaveInput saveInput) {
        return authUtil.getUserName()
                .flatMap(userName ->
                        mapper.toModel(saveInput, userName).flatMap(repository::save)
                );
    }

    @Override
    public Mono<Doc> update(DocUpdateInput updateInput) {
        return repository.findById(updateInput.id())
                .flatMap(doc -> authUtil.isCurrentUser(doc.getOwner())
                        .then(mapper.toUpdatedModel(doc, updateInput)
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
    public Mono<Doc> findById(String id) {
        return repository.findById(id)
                .flatMap(collection -> authUtil.isCurrentUser(collection.getOwner())
                        .then(Mono.just(collection))
                );
    }

    @Override
    public Flux<Doc> search(String collectionId) {
        return Flux.empty();
    }

    @Override
    public Mono<Doc> linkDoc(String targetDocId, String docId) {
        return repository.findById(targetDocId)
                .flatMap(targetDoc -> authUtil.isCurrentUser(targetDoc.getOwner())
                        .then(repository.findById(docId))
                        .flatMap(doc -> authUtil.isCurrentUser(doc.getOwner())
                                .then(Mono.defer(() -> {
                                    targetDoc.linkDoc(new DocInfo(doc.getId(), doc.getTitle()));
                                    return repository.save(targetDoc);
                                }))
                        )
                );
    }
}
