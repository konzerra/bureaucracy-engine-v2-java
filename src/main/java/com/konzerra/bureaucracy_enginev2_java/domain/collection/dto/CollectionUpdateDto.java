package com.konzerra.bureaucracy_enginev2_java.domain.collection.dto;

import org.jetbrains.annotations.NotNull;

public record CollectionUpdateDto(
        @NotNull
        String name,
        @NotNull
        String description
) {
}
