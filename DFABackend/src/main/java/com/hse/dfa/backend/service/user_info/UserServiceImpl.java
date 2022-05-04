package com.hse.dfa.backend.service.user_info;

import com.hse.dfa.backend.controller.dto.user_info.UserInfoForUpdateDTO;
import com.hse.dfa.backend.controller.dto.user_info.UserViewDTO;
import com.hse.dfa.backend.exceptions.authentication.NotAuthenticatedRequestException;
import com.hse.dfa.backend.exceptions.userInfo.IssuerCanNotChangeEthereumInfoException;
import com.hse.dfa.backend.model.user_info.RoleType;
import com.hse.dfa.backend.model.user_info.User;
import com.hse.dfa.backend.repository.user_info.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

import static com.hse.dfa.backend.util.converters.user.UserConverter.toUserViewDTO;
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

    @Override
    public UserViewDTO getCurrentUserInfo() {
        final var user = getCurrentUser();
        return toUserViewDTO(user);
    }

    @Override
    public void updateUserInfo(UserInfoForUpdateDTO dto) {
        final var user = getCurrentUser();
        if (!dto.getPrivateKey().equals(user.getPrivateKey().orElse("")) ||
            !dto.getAddress().equals(user.getAddress().orElse(""))) {
            if (
                user.getUserRoles().stream()
                    .anyMatch(
                        userRole -> userRole.getRole().getRoleType().equals(RoleType.ISSUER)
                    )
            ) {
                throw new IssuerCanNotChangeEthereumInfoException("Issuer can not change ethereum info!");
            }
        }
        user.setEmail(dto.getEmail());
        user.setAddress(dto.getAddress());
        user.setPrivateKey(dto.getPrivateKey());
        userRepository.saveAndFlush(user);
    }
}
