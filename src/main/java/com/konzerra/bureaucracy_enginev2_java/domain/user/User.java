package com.konzerra.bureaucracy_enginev2_java.domain.user;

import com.konzerra.bureaucracy_enginev2_java.domain.user.role.Role;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Collections;
import java.util.List;

@Document
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @Builder.Default
    private String id = null;

    @Builder.Default
    private String name = "";

    @Builder.Default
    @Indexed(unique = true)
    private String email = "";

    @Builder.Default
    private String password = "";

    @Builder.Default
    private Boolean enabled = true;

    @Builder.Default
    private List<Role> roles = Collections.emptyList();

    public boolean isEnabled(){
        return this.enabled;
    }
}
