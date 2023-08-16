package com.konzerra.bureaucracy_enginev2_java.domain.doc.dto;

import com.konzerra.bureaucracy_enginev2_java.domain.doc.Doc;
import com.konzerra.bureaucracy_enginev2_java.domain.doc.DocInfo;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public record DocSaveDto(

        @NotNull
        String title,
        @NotNull
        String version,
        @NotNull
        String collection,
        @NotNull
        List<Doc.DocContent> content,
        @NotNull
        List<String> notes,
        @NotNull
        List<DocInfo> attachedDocs

) {
}
