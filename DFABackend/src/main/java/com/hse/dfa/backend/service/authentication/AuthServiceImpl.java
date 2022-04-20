package com.hse.dfa.backend.service.authentication;

import com.hse.dfa.backend.controller.dto.authentication.TokenResponse;
import com.hse.dfa.backend.controller.dto.user_info.UserInfoForCreateDTO;
import com.hse.dfa.backend.exceptions.authentication.UsernameIsAlreadyExistException;
import com.hse.dfa.backend.model.user_info.User;
import com.hse.dfa.backend.repository.user_info.RoleRepository;
import com.hse.dfa.backend.repository.user_info.UserRepository;
import com.hse.dfa.backend.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Key;
import java.util.Base64;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import static com.hse.dfa.backend.model.user_info.RoleType.defaultRoleType;
import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public TokenResponse generateAuthToken(String username, String password) {
        final var authentication =
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
            );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final var user = (UserDetails) authentication.getPrincipal();
        List<String> roles = user.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .toList();
        String token = jwtTokenProvider.createToken(username, roles);
        return new TokenResponse(
            token,
            user
        );
    }

    @Override
    @Transactional
    //TODO Шифрование приватного ключа
    public void createNewAccount(UserInfoForCreateDTO dto) {
        if (userRepository.existsByUsername(dto.getUsername())) {
            throw new UsernameIsAlreadyExistException(
                format("User with username = %s is already exist", dto.getUsername())
            );
        }
        final var defaultRoleType = defaultRoleType();
        final var defaultRole = roleRepository.findByRoleType(defaultRoleType)
            .orElseThrow(
                () -> new NoSuchElementException(
                    format("No Role with RoleType = %s", defaultRoleType.getRoleName())
                )
            );
        final var user = new User(
            dto.getUsername(),
            passwordEncoder.encode(dto.getPassword()),
            dto.getAddress(),
            dto.getPrivateKey(),
            Set.of(defaultRole)
        );
        userRepository.saveAndFlush(user);
    }
}
