package com.konzerra.bureaucracy_enginev2_java.domain.doc;

import com.konzerra.bureaucracy_enginev2_java.domain.doc.dto.DocSaveInput;
import com.konzerra.bureaucracy_enginev2_java.domain.doc.dto.DocUpdateInput;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DocService {


    Mono<Doc> save(DocSaveInput saveInput);


    Mono<Doc> update(DocUpdateInput updateInput);


    Mono<Void> deleteById(String id);


    Mono<Doc> findById(String id);


    Flux<Doc> search(String collectionId);

    Mono<Doc> linkDoc(String targetDocId, String docId);
}
