package com.hse.dfa.backend.model.user_info;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import static java.lang.String.format;
import static java.util.Optional.ofNullable;
import static java.util.function.Function.identity;

@RequiredArgsConstructor
@Getter
public enum RoleType {
    ADMIN(RoleName.ADMIN),
    ISSUER(RoleName.ISSUER),
    TRADER(RoleName.TRADER);

    private static final Map<String, RoleType> ROLE_NAME_MAP =
        Arrays.stream(RoleType.values())
            .collect(Collectors.toMap(RoleType::getRoleName, identity()));

    public static RoleType fromRoleName(String roleName) {
        return ofNullable(ROLE_NAME_MAP.get(roleName))
            .orElseThrow(() -> new IllegalArgumentException(
                format("Cannot parse RoleType from name '%s'", roleName)
            ));
    }

    private final String roleName;
}
