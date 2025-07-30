package com.platform.auth.auth_service.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.platform.auth.auth_service.model.UserRepository;

@Configuration
public class UserDetailsServiceConfig implements UserDetailsService{

    private final UserRepository userRepo;

    public UserDetailsServiceConfig(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    // @Bean
    // public UserDetailsService userDetailsService() {
    //     return username -> userRepo.findByUsername(username)
    //         .map(user -> org.springframework.security.core.userdetails.User
    //             .withUsername(user.getUsername())
    //             .password(user.getPassword())
    //             .authorities("USER")
    //             .build())
    //         .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    // }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.platform.auth.auth_service.model.User user = userRepo.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        
        return org.springframework.security.core.userdetails.User
            .withUsername(user.getUsername())
            .password(user.getPassword())
            .authorities("USER")
            .build();
    }
}
