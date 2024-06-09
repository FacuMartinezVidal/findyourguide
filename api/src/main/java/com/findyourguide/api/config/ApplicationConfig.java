package com.findyourguide.api.config;

import com.findyourguide.api.entity.User;
import com.findyourguide.api.repository.GuideRepository;
import com.findyourguide.api.repository.TouristRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {
    private final TouristRepository touristRepository;
    private final GuideRepository guideRepository;

    @Bean
    public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            // Buscar en el repositorio de Tourist
            UserDetails user = touristRepository.findUserByUsername(username)
                    .map(this::mapToUserDetails)
                    .orElse(null);

            if (user != null) {
                return user;
            }

            // Buscar en el repositorio de Guide
            user = guideRepository.findUserByUsername(username)
                    .map(this::mapToUserDetails)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));

            return user;
        };
    }

    private UserDetails mapToUserDetails(User user) {
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.isActive(),
                true,
                true,
                true,
                List.of(new SimpleGrantedAuthority(user.getRole().name()))
        );
    }
}
