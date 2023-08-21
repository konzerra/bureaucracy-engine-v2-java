package com.konzerra.bureaucracy_enginev2_java.domain.collection.dto;

import org.jetbrains.annotations.NotNull;

public record CollectionUpdateInput(

        @NotNull
        String id,
        @NotNull
        String name,
        @NotNull
        String description
) {
}
