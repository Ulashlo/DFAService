package com.hse.dfa.backend.repository.user_info;

import com.hse.dfa.backend.model.user_info.Role;
import com.hse.dfa.backend.model.user_info.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleType(RoleType roleType);
}
