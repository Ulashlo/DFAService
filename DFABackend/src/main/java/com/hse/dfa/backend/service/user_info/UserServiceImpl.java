package com.hse.dfa.backend.service.user_info;

import com.hse.dfa.backend.exceptions.authentication.NotAuthenticatedRequestException;
import com.hse.dfa.backend.model.user_info.User;
import com.hse.dfa.backend.repository.user_info.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

import static java.lang.String.format;
import static java.util.Optional.ofNullable;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public User getCurrentUser() {
        final var authentication =
            ofNullable(SecurityContextHolder.getContext().getAuthentication())
                .orElseThrow(() -> new NotAuthenticatedRequestException(
                    "User has not been authenticated yet"
                ));
        final var userDetails = (UserDetails) authentication.getPrincipal();
        return userRepository.findByUsername(userDetails.getUsername())
            .orElseThrow(() -> new NoSuchElementException(
                format("User with username = %s not found in database!", userDetails.getUsername())
            ));
    }
}
