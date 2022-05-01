package com.hse.dfa.backend.repository.admin_requests;

import com.hse.dfa.backend.controller.dto.admin_requests.IssuerRequestDTO;

import java.util.List;

public interface CustomIssuerRequestRepository {
    List<IssuerRequestDTO> getAllCreatedIssuerRequest();
}
