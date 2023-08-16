package com.konzerra.bureaucracy_enginev2_java.on_startup;

import com.konzerra.bureaucracy_enginev2_java.domain.user.User;
import com.konzerra.bureaucracy_enginev2_java.domain.user.UserRepository;
import com.konzerra.bureaucracy_enginev2_java.domain.user.role.Role;
import com.konzerra.bureaucracy_enginev2_java.domain.user.role.RoleNames;
import com.konzerra.bureaucracy_enginev2_java.domain.user.role.RoleRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Collections;

@Component
public class OnStartUp implements ApplicationRunner {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public OnStartUp(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        createUserOrDoNothing().subscribe();
    }
    private Mono<User> createUserOrDoNothing() {
        return userRepository.findUserByEmail("konzerra@gmail.com")
                .switchIfEmpty(
                        findOrCreateRole(RoleNames.ADMIN.getName())
                                .flatMap(role -> userRepository.save(
                                        User.builder()
                                                .email("konzerra@gmail.com")
                                                .password(passwordEncoder.encode("123456"))
                                                .roles(Collections.singletonList(role))
                                                .build()
                                ))
                );
    }


    private  Mono<Role> findOrCreateRole(String roleName) {
        return roleRepository.findByName(roleName)
                .switchIfEmpty(Mono.defer(() -> createRole(roleName)));
    }

    private Mono<Role> createRole(String roleName) {
        Role newRole = new Role();
        newRole.setName(roleName);
        return roleRepository.save(newRole);
    }
}
