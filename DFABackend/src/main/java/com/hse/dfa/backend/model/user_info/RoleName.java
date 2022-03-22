package com.hse.dfa.backend.model.user_info;

import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class RoleName {
    public static final String ADMIN = "ROLE_ADMIN";
    public static final String ISSUER = "ROLE_ISSUER";
    public static final String TRADER = "ROLE_TRADER";
}
