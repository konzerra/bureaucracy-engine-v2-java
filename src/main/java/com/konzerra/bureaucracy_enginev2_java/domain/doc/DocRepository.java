package com.konzerra.bureaucracy_enginev2_java.domain.doc;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocRepository extends ReactiveMongoRepository<Doc, String> {
}
