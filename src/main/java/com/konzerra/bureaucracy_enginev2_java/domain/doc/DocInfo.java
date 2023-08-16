package com.konzerra.bureaucracy_enginev2_java.domain.doc;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public record DocInfo(
        String title,
        String docId
) {
}
