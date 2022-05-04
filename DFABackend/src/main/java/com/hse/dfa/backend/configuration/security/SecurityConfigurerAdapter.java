package com.hse.dfa.backend.configuration.security;

import com.hse.dfa.backend.security.JwtEntryPoint;
import com.hse.dfa.backend.security.JwtTokenFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.header.writers.StaticHeadersWriter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true, jsr250Enabled = true)
public class SecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
    private static final String LOGIN_ENTRY_POINT = "/auth/**";
    private static final String SAFE_ENTRY_POINT = "/safe/**";
    private static final String UNSAFE_ENTRY_POINT = "/unsafe/**";

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenFilter jwtTokenFilter;
    private final JwtEntryPoint unauthorizedHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http = http.cors().and().csrf().disable();
        http = http
            .headers()
            .addHeaderWriter(new StaticHeadersWriter("Access-Control-Expose-Headers", "X-Server-Version"))
            .and();
        http = http
            .sessionManagement()
            .sessionCreationPolicy(STATELESS)
            .and();
        http = http
            .exceptionHandling()
            .authenticationEntryPoint(unauthorizedHandler)
            .and();
        http.authorizeRequests()
            .antMatchers(LOGIN_ENTRY_POINT).permitAll()
            .antMatchers(UNSAFE_ENTRY_POINT).permitAll()
            .antMatchers(SAFE_ENTRY_POINT).authenticated();
        http.addFilterBefore(
            jwtTokenFilter,
            UsernamePasswordAuthenticationFilter.class
        );
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
