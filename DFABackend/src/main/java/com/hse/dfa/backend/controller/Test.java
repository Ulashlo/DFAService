package com.hse.dfa.backend.controller;

import com.hse.dfa.backend.contracts.DFA;
import com.hse.dfa.backend.model.user_info.User;
import com.hse.dfa.backend.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class Test {
    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
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
        return dfa.owner().send();
    }

    @GetMapping("/qwe")
    public String qwe() {
        return "qwe";
    }
    @GetMapping("/auth/rty")
    public String rty() {
        return "rty";
    }

    @GetMapping(value = "/login")
    public ResponseEntity<?> login(@RequestParam String nickname, @RequestParam String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(nickname, password));
        UserDetails user = userDetailsService.loadUserByUsername(nickname);
        Map<Object, Object> model = new HashMap<>();
        if (user != null) {
            List<String> roles = new ArrayList<>(
                user.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .toList()
            );
            String token = jwtTokenProvider.createToken(nickname, roles);
            model.put("user", user);
            model.put("token", token);
            return new ResponseEntity<>(model, HttpStatus.OK);
        } else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping
    public String getPassword(@RequestParam String password) {
        return passwordEncoder.encode(password);
    }

}
