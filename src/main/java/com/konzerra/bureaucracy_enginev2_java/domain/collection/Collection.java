package com.konzerra.bureaucracy_enginev2_java.domain.collection;

import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collections;
import java.util.List;

@Document
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Collection {

    @Id
    @Builder.Default
    private String id = null;

    @Builder.Default
    private String name = "";

    @Builder.Default
    private @NotNull String description = "";

    @Builder.Default
    private String owner = "";

    @Builder.Default
    private List<DocInfo> docs = Collections.emptyList();


    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class DocInfo {
        private String title;
        private String docId;
    }
}
