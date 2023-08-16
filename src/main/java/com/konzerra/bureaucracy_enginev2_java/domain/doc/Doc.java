package com.konzerra.bureaucracy_enginev2_java.domain.doc;

import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Document
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Doc {

    @Id
    private String id;

    @Builder.Default
    private String title = "";

    @Builder.Default
    private String version = "";

    @Builder.Default
    private String collection = "";

    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    private Date updatedAt;

    @Builder.Default
    @NotNull
    private List<DocContent> content = Collections.emptyList();

    @Builder.Default
    @NotNull
    private List<String> notes = Collections.emptyList();

    @Builder.Default
    @NotNull
    private List<DocInfo> attachedDocs = Collections.emptyList();

    @Builder.Default
    @NotNull
    private List<DocInfo> previousVersions = Collections.emptyList();




    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class DocContent {
        private String heading;
        private String paragraph;
        private String note;
    }

}


