package com.hse.dfa.backend.service.request;

import com.hse.dfa.backend.controller.dto.admin_requests.IssuerRequestDTO;

import java.util.List;

/**
 * Service that provide actions with requests.
 */
public interface RequestService {
    /**
     * Add request for issuer rights for authenticated user.
     */
    void addIssuerRequest();

    /**
     * Return all created issuer requests.
     *
     * @return created issuer requests
     */
    List<IssuerRequestDTO> getAllCreatedIssuerRequest();

    /**
     * Accept issuer requests.
     */
    void acceptIssuerRequest(Long requestId);

    /**
     * Cancel issuer requests.
     */
    void cancelIssuerRequest(Long requestId);
}
