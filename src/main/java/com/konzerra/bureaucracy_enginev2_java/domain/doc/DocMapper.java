package com.konzerra.bureaucracy_enginev2_java.domain.doc;


import com.konzerra.bureaucracy_enginev2_java.domain.doc.dto.DocSaveInput;
import com.konzerra.bureaucracy_enginev2_java.domain.doc.dto.DocUpdateInput;
import reactor.core.publisher.Mono;

public interface DocMapper {
    Mono<Doc> toUpdatedModel(Doc collection, DocUpdateInput updateInput);

    Mono<Doc> toModel(DocSaveInput saveInput, String userName);
}
