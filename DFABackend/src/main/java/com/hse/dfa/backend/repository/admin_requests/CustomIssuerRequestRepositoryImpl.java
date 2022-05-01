package com.hse.dfa.backend.repository.admin_requests;

import com.hse.dfa.backend.controller.dto.admin_requests.IssuerRequestDTO;
import com.hse.dfa.backend.controller.dto.user_info.UserLinkDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Tuple;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class CustomIssuerRequestRepositoryImpl implements CustomIssuerRequestRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<IssuerRequestDTO> getAllCreatedIssuerRequest() {
        return entityManager.createQuery(
                "select ir.id as id, " +
                    "           ir.dateCreated as dateCreated, " +
                    "           u.username as username " +
                    "from IssuerRequest ir join ir.userWhoSent u " +
                    "where ir.status = 'CREATED'", Tuple.class
            ).getResultList().stream()
            .map(tuple -> new IssuerRequestDTO(
                tuple.get("id", Long.class),
                new UserLinkDTO(
                    tuple.get("username", String.class),
                    "email"
                ),
                tuple.get("dateCreated", OffsetDateTime.class)
            )).collect(Collectors.toList());
    }
}
