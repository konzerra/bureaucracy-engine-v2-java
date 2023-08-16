package com.konzerra.bureaucracy_enginev2_java.domain.doc;

import com.konzerra.bureaucracy_enginev2_java.domain.doc.dto.DocSaveDto;
import com.konzerra.bureaucracy_enginev2_java.domain.doc.dto.DocUpdateDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DocService {

    // Save a new Collection
    Mono<Doc> save(DocSaveDto saveDto);

    // Update an existing Collection
    Mono<Doc> update(DocUpdateDto updateDto);

    // Delete a Collection by ID
    Mono<Void> deleteById(String id);

    // Find a Collection by ID
    Mono<Doc> findById(String id);

    // Find all Docs with pagination
    Flux<Doc> findAll(int pageSize, int pageNumber);

    Mono<DocInfo> linkDoc(String docId);
}
