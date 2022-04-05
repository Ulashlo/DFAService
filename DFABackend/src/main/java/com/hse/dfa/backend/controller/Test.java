package com.hse.dfa.backend.controller;

import com.hse.dfa.backend.contracts.DFA;
import com.hse.dfa.backend.model.user_info.RoleName;
import com.hse.dfa.backend.service.authentication.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.math.BigInteger;

@RestController
@RequiredArgsConstructor
@RolesAllowed(RoleName.ADMIN)
public class Test {
    private final DFA dfa;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("safe/mint/{amount}")
    public void createEvent(@PathVariable int amount) {
        try {
            dfa.mint(BigInteger.valueOf(amount)).send();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GetMapping("safe/owner")
    public String owner() throws Exception {
        return dfa.owner().send();
    }

    @GetMapping("/unsafe/qwe")
    public String qwe() {
        return "qwe";
    }

    @GetMapping("/safe/rty")
    public String rty() {
        return "rty";
    }

    @PostMapping
    public String getPassword(@RequestParam String password) {
        return passwordEncoder.encode(password);
    }
}
