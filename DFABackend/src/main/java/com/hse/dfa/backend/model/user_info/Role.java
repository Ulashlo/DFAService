package com.hse.dfa.backend.model.user_info;

import com.hse.dfa.backend.util.converters.attribute_converters.RoleTypeAttributeConverter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "role", schema ="user_info")
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private long id;

    @Convert(converter = RoleTypeAttributeConverter.class)
    @Column(name = "name", unique = true)
    private RoleType roleType;

    @Column(name = "description")
    private String description;

    @Override
    public String getAuthority() {
        return roleType.getRoleName();
    }
}
