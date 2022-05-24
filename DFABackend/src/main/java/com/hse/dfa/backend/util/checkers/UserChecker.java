package com.hse.dfa.backend.util.checkers;

import com.hse.dfa.backend.exceptions.contract.UserEthereumCredentialException;
import com.hse.dfa.backend.exceptions.userInfo.WrongCredentialsException;
import com.hse.dfa.backend.model.user_info.User;
import lombok.NoArgsConstructor;
import org.web3j.crypto.Credentials;

import java.util.Locale;

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

    public static void checkCredentials(String address, String privateKey) {
        final var addressExist = address != null && address.length() > 0;
        final var keyExist = privateKey != null && privateKey.length() > 0;
        if (addressExist && !keyExist || !addressExist && keyExist) {
            throw new WrongCredentialsException("And address and key should exist!");
        }
        if (keyExist && addressExist) {
            final var credentials = Credentials.create(privateKey);
            if (!credentials.getAddress().toLowerCase(Locale.ROOT).equals(address.toLowerCase(Locale.ROOT))) {
                throw new WrongCredentialsException("Wrong credentials!");
            }
        }
    }
}
