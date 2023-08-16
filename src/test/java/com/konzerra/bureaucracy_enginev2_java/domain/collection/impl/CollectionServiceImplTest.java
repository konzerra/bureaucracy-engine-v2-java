package com.konzerra.bureaucracy_enginev2_java.domain.collection.impl;

import com.konzerra.bureaucracy_enginev2_java.domain.collection.Collection;
import com.konzerra.bureaucracy_enginev2_java.domain.collection.CollectionRepository;
import com.konzerra.bureaucracy_enginev2_java.domain.collection.dto.CollectionSaveDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CollectionServiceImplTest {

    @Mock
    private CollectionRepository collectionRepository;

    @InjectMocks
    private CollectionServiceImpl collectionService;

    @BeforeEach
    void setUp() {

    }

    @Test
    void save() {
        CollectionSaveDto saveDto = new CollectionSaveDto("Test Collection", "Test Description");
        Collection expectedCollection = new Collection("TestId", "Test Collection", "Test Description", "", Collections.emptyList());

        // Mock the behavior of the repository
        when(collectionRepository.save(any(Collection.class))).thenReturn(Mono.just(expectedCollection));

        // Call the method under test
        Mono<Collection> result = collectionService.save(saveDto);

        // Assertions
        StepVerifier.create(result)
                .expectNext(expectedCollection)
                .verifyComplete();
    }

    @Test
    void update() {
        Collection existingCollection = new Collection("TestId", "Test Collection", "Test Description", "", Collections.emptyList());
        Collection updatedCollection = new Collection("TestId", "Updated Collection", "Updated Description", "", Collections.emptyList());

        // Mock the behavior of the repository
        when(collectionRepository.save(any(Collection.class))).thenReturn(Mono.just(updatedCollection));

        // Call the method under test
        Mono<Collection> result = collectionService.update(existingCollection);

        // Assertions
        StepVerifier.create(result)
                .expectNext(updatedCollection)
                .verifyComplete();
    }

    @Test
    void deleteById() {
        String idToDelete = "TestId";

        // Mock the behavior of the repository
        when(collectionRepository.deleteById(idToDelete)).thenReturn(Mono.empty());

        // Call the method under test
        Mono<Void> result = collectionService.deleteById(idToDelete);

        // Assertions
        StepVerifier.create(result).verifyComplete();
    }

    @Test
    void findById() {
        String idToFind = "TestId";
        Collection expectedCollection = new Collection("TestId", "Test Collection", "Test Description", "", Collections.emptyList());

        // Mock the behavior of the repository
        when(collectionRepository.findById(idToFind)).thenReturn(Mono.just(expectedCollection));

        // Call the method under test
        Mono<Collection> result = collectionService.findById(idToFind);

        // Assertions
        StepVerifier.create(result)
                .expectNext(expectedCollection)
                .verifyComplete();
    }

    @Test
    void findAll() {
        Collection collection1 = new Collection("TestId1", "Test Collection 1", "Test Description 1", "", Collections.emptyList());
        Collection collection2 = new Collection("TestId2", "Test Collection 2", "Test Description 2", "", Collections.emptyList());
        Flux<Collection> expectedCollections = Flux.just(collection1, collection2);

        // Mock the behavior of the repository
        when(collectionRepository.findAll()).thenReturn(expectedCollections);

        // Call the method under test
        Flux<Collection> result = collectionService.findAll();

        // Assertions
        StepVerifier.create(result)
                .expectNext(collection1)
                .expectNext(collection2)
                .verifyComplete();
    }

    @Test
    void testFindAll() {
        // This test method demonstrates how to test the paginated findAll method.

        int pageSize = 2;
        int pageNumber = 1;

        Collection collection1 = new Collection("TestId1", "Test Collection 1", "Test Description 1", "", Collections.emptyList());
        Collection collection2 = new Collection("TestId2", "Test Collection 2", "Test Description 2", "", Collections.emptyList());
        Collection collection3 = new Collection("TestId3", "Test Collection 3", "Test Description 3", "", Collections.emptyList());

        Flux<Collection> allCollections = Flux.just(collection1, collection2, collection3);

        // Mock the behavior of the repository for findAll method
        when(collectionRepository.findAll()).thenReturn(allCollections);

        // Call the method under test
        Flux<Collection> result = collectionService.findAll(pageSize, pageNumber);

        // Assertions
        StepVerifier.create(result)
                .expectNext(collection3) // Skipping the first page, so only the third collection should be returned.
                .verifyComplete();
    }
}