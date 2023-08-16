package com.konzerra.bureaucracy_enginev2_java.domain.collection.dto;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.data.annotation.Id;

public record CollectionSaveDto(
        @NotNull
        String name,
        @NotNull
        String description
) {
}
