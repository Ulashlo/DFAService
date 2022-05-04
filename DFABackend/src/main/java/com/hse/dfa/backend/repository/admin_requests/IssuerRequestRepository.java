package com.hse.dfa.backend.repository.admin_requests;

import com.hse.dfa.backend.model.admin_requests.IssuerRequest;
import com.hse.dfa.backend.model.admin_requests.IssuerRequestStatus;
import com.hse.dfa.backend.model.user_info.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IssuerRequestRepository extends
    JpaRepository<IssuerRequest, Long>,
    CustomIssuerRequestRepository {
    Optional<IssuerRequest> findByUserWhoSentAndStatus(User userWhoSent, IssuerRequestStatus status);
}
