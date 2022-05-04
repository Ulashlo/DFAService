package com.hse.dfa.backend.service.request;

import com.hse.dfa.backend.controller.dto.admin_requests.IssuerRequestDTO;
import com.hse.dfa.backend.model.admin_requests.IssuerRequest;
import com.hse.dfa.backend.model.admin_requests.IssuerRequestStatus;
import com.hse.dfa.backend.repository.admin_requests.IssuerRequestRepository;
import com.hse.dfa.backend.repository.user_info.RoleRepository;
import com.hse.dfa.backend.repository.user_info.UserRepository;
import com.hse.dfa.backend.service.user_info.UserService;
import com.hse.dfa.backend.util.checkers.UserChecker;
import com.hse.dfa.backend.util.contracts.ContractFabric;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

import static com.hse.dfa.backend.model.admin_requests.IssuerRequestStatus.ACCEPTED;
import static com.hse.dfa.backend.model.admin_requests.IssuerRequestStatus.CANCELLED;
import static com.hse.dfa.backend.model.user_info.RoleType.ISSUER;
import static java.time.OffsetDateTime.now;

@Service
@RequiredArgsConstructor
public class RequestServiceImpl implements RequestService {
    private final IssuerRequestRepository issuerRequestRepository;
    private final UserService userService;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ContractFabric contractFabric;

    @Override
    public void addIssuerRequest() {
        final var currentUser = userService.getCurrentUser();
        final var request = new IssuerRequest(currentUser);
        issuerRequestRepository.saveAndFlush(request);
    }

    @Override
    @Transactional(readOnly = true)
    public List<IssuerRequestDTO> getAllCreatedIssuerRequest() {
        return issuerRequestRepository.getAllCreatedIssuerRequest();
    }

    @Override
    @Transactional
    public void acceptIssuerRequest(Long requestId) throws Exception {
        final var factory = contractFabric.loadFactory();
        final var request = issuerRequestRepository.findById(requestId)
            .orElseThrow(() -> new NoSuchElementException(
                String.format("Issuer request with id=%d not found!", requestId)
            ));
        processIssuerRequest(request, ACCEPTED);
        factory.verifyUser(UserChecker.checkAddress(request.getUserWhoSent())).send();
    }

    @Override
    @Transactional
    public void cancelIssuerRequest(Long requestId) {
        final var request = issuerRequestRepository.findById(requestId)
            .orElseThrow(() -> new NoSuchElementException(
                String.format("Issuer request with id=%d not found!", requestId)
            ));
        processIssuerRequest(request, CANCELLED);
    }

    private void processIssuerRequest(IssuerRequest request, IssuerRequestStatus status) {
        final var user = request.getUserWhoSent();
        final var issuerRole = roleRepository.findByRoleType(ISSUER)
            .orElseThrow(() -> new NoSuchElementException("No role with issuer role type!"));
        user.addRole(issuerRole);
        userRepository.saveAndFlush(user);
        request.setStatus(status);
        request.setAdminWhoAnswered(userService.getCurrentUser());
        request.setDateAnswered(now());
        issuerRequestRepository.saveAndFlush(request);
    }
}
