package com.konzerra.bureaucracy_enginev2_java.domain.user.role;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Role {
    @Id
    @Builder.Default
    private String id = null;

    @Builder.Default
    private String name = "";
}
