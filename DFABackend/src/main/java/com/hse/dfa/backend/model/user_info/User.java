package com.hse.dfa.backend.model.user_info;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;
import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.IDENTITY;
import static org.hibernate.annotations.FetchMode.SUBSELECT;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user", schema ="user_info")
public class User implements UserDetails {
    @Id
    @Getter
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private long id;

    @Getter
    @Column(name = "username", unique = true)
    private String username;

    @Getter
    @Column(name = "password")
    private String password;

    @Column(name = "address")
    private String address;

    @OneToMany(mappedBy = "user", orphanRemoval = true, cascade = ALL, fetch = EAGER)
    @Fetch(SUBSELECT)
    private Set<UserRole> userRoles = new HashSet<>();

    public User(String username, String password, Set<Role> userRoles) {
        this.username = username;
        this.password = password;
        this.addRoles(userRoles);
    }

    public Optional<String> getAddress() {
        return ofNullable(address);
    }

    public void addRoles(Set<Role> roles) {
        roles.forEach(this::addRole);
    }

    public void addRole(Role role) {
        if (
            userRoles.stream().noneMatch(
                userRole -> userRole.getRole().equals(role)
            )
        ) {
            final var userRole = new UserRole(this, role);
            this.userRoles.add(userRole);
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userRoles.stream()
            .map(UserRole::getRole)
            .collect(toList());
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
