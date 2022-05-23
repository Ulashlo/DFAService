package com.hse.dfa.backend.service.exchanger;

import com.hse.dfa.backend.contracts.Exchanger;
import com.hse.dfa.backend.controller.dto.dfa.AllRequestsDTO;
import com.hse.dfa.backend.controller.dto.dfa.RequestsForDFADTO;
import com.hse.dfa.backend.controller.dto.exchanger.ExchangeInfoForDeleteDTO;
import com.hse.dfa.backend.controller.dto.exchanger.ExchangeRequestDTO;
import com.hse.dfa.backend.service.user_info.UserService;
import com.hse.dfa.backend.util.contracts.ContractFabric;
import com.hse.dfa.backend.util.converters.contract.DFAInfoConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;

import static com.hse.dfa.backend.util.checkers.UserChecker.checkPrivateKey;
import static java.lang.String.format;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExchangerServiceImpl implements ExchangerService {
    private final ContractFabric contractFabric;
    private final UserService userService;

    @Override
    public void addExchangeRequest(ExchangeRequestDTO dto) throws Exception {
        final var user = userService.getCurrentUser();
        final var privateKey = checkPrivateKey(user);
        final var factory = contractFabric.loadFactory(privateKey);
        final var exchangerAddress = factory.getExchanger(dto.getDfaToGive()).send();
        final var exchanger = contractFabric.loadExchanger(privateKey, exchangerAddress);
        final var dfa = contractFabric.loadDfa(privateKey, dto.getDfaToGive());
        dfa.approve(exchangerAddress, BigInteger.valueOf(dto.getAmountToGive())).send();
        exchanger.addRequest(
            new Exchanger.AddExchangeRequestParams(
                dto.getType().getCode(),
                dto.getDfaToGet(),
                BigInteger.valueOf(dto.getAmountToGet()),
                BigInteger.valueOf(dto.getAmountToGive()),
                BigInteger.valueOf(dto.getEndTime())
            )
        ).send();
    }

    @Override
    public List<AllRequestsDTO> getAllRequests() throws Exception {
        final var factory = contractFabric.loadFactory();
        final var dfas = (List<String>) factory.getAllDfaAddresses().send();
        final var result = new LinkedList<AllRequestsDTO>();
        for (String dfaToGive : dfas) {
            final var requestsToGetInfo = new LinkedList<RequestsForDFADTO>();
            final var exchangerAddress = factory.getExchanger(dfaToGive).send();
            final var exchanger = contractFabric.loadExchanger(exchangerAddress);
            for (String dfaToGet : dfas) {
                if (dfaToGet.equals(dfaToGive)) {
                    continue;
                }
                log.info(format(
                    "Trying to load requests from %s with dfa to give = %s dfa to get = %s",
                    exchangerAddress,
                    dfaToGive,
                    dfaToGet
                ));
                final var requestInfo =
                    DFAInfoConverter.tupleToRequestInfoDTO(
                        exchanger.getRequestsByDfa(dfaToGet).send()
                    );
                if (requestInfo.size() > 0) {
                    requestsToGetInfo.add(
                        new RequestsForDFADTO(
                            dfaToGet,
                            requestInfo
                        )
                    );
                }
            }
            if (requestsToGetInfo.size() > 0) {
                result.add(
                    new AllRequestsDTO(
                        dfaToGive,
                        requestsToGetInfo
                    )
                );
            }
        }
        return result;
    }

    @Override
    public void closeRequest(ExchangeInfoForDeleteDTO info) throws Exception {
        final var user = userService.getCurrentUser();
        final var privateKey = checkPrivateKey(user);
        final var factory = contractFabric.loadFactory(privateKey);
        factory.closeRequest(
            info.getDfaToGetAddress(),
            info.getDfaToGiveAddress(),
            BigInteger.valueOf(info.getIndex())
        ).send();
    }
}
