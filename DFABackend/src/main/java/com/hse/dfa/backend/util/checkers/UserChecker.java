package com.hse.dfa.backend.util.checkers;

import com.hse.dfa.backend.exceptions.contract.UserEthereumCredentialException;
import com.hse.dfa.backend.model.user_info.User;
import lombok.NoArgsConstructor;

import static java.lang.String.format;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class UserChecker {
    public static String checkAddress(User user) {
        return user.getAddress().orElseThrow(
            () -> new UserEthereumCredentialException(
                format(
                    "User with username = %s dont have ethereum address!",
                    user.getUsername()
                )
            )
        );
    }

    public static String checkPrivateKey(User user) {
        return user.getPrivateKey().orElseThrow(
            () -> new UserEthereumCredentialException(
                format(
                    "User with username = %s dont have ethereum private key!",
                    user.getUsername()
                )
            )
        );
    }
}
