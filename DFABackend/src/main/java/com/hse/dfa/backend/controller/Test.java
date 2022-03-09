package com.hse.dfa.backend.controller;

import com.hse.dfa.backend.contracts.DFA;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@RestController
@RequiredArgsConstructor
public class Test {
    private final DFA dfa;

    @PostMapping("/mint/{amount}")
    public void createEvent(@PathVariable int amount) {
        try {
            dfa.mint(BigInteger.valueOf(amount)).send();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/owner")
    public String owner() throws Exception {
        int i = 0;
        return dfa.owner().send();
    }
}
