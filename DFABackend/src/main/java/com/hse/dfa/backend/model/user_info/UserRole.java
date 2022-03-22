package com.hse.dfa.backend.model.user_info;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "user_role", schema ="user_info")
public class UserRole {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "id")
    @Column(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "id")
    @Column(name = "role_id")
    private Role role;
}
