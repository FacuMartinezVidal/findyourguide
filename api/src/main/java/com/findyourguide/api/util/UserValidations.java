package com.findyourguide.api.util;

import org.springframework.stereotype.Component;

import com.findyourguide.api.config.SecurityContextService;
import com.findyourguide.api.entity.Role;
import com.findyourguide.api.entity.User;
import com.findyourguide.api.error.UnauthorizeCredentialsException;
import com.findyourguide.api.error.UserNotFoundException;
import com.findyourguide.api.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserValidations {

    private final UserRepository userRepository;

    private final SecurityContextService contextService;

    public void validateRole(Role requiredRole) {
        String username = contextService.getAuthenticatedUser();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException());

        if (!requiredRole.equals(user.getRole())) {
            throw new UnauthorizeCredentialsException();
        }
    }
}
