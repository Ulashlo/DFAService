package com.hse.dfa.backend.repository.admin_requests;

import com.hse.dfa.backend.model.admin_requests.IssuerRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IssuerRequestRepository extends
    JpaRepository<IssuerRequest, Long>,
    CustomIssuerRequestRepository {
}
