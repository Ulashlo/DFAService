package com.hse.dfa.backend.util.converters.contract;

import com.hse.dfa.backend.contracts.ExchangeType;
import com.hse.dfa.backend.controller.dto.dfa.DFABalanceDTO;
import com.hse.dfa.backend.controller.dto.dfa.DFAViewDTO;
import com.hse.dfa.backend.controller.dto.dfa.ExchangeInfo;
import com.hse.dfa.backend.exceptions.contract.IncorrectEthereumResponseFormatException;
import lombok.NoArgsConstructor;
import org.web3j.tuples.generated.Tuple2;
import org.web3j.tuples.generated.Tuple3;
import org.web3j.tuples.generated.Tuple5;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class DFAInfoConverter {
    public static List<DFAViewDTO> tupleToDFAViewDTO(
        Tuple5<List<String>, List<String>, List<String>, List<String>, List<BigInteger>> tuple
    ) {
        final var addresses = tuple.component1();
        final var owners = tuple.component2();
        final var names = tuple.component3();
        final var symbols = tuple.component4();
        final var supplies = tuple.component5();
        int length = addresses.size();
        if (owners.size() != length || names.size() != length ||
            symbols.size() != length || supplies.size() != length) {
            throw new IncorrectEthereumResponseFormatException(
                "Arrays for dfa info list has different size!"
            );
        }
        final var result = new LinkedList<DFAViewDTO>();
        for (int i = 0; i < length; i++) {
            result.add(
                new DFAViewDTO(
                    addresses.get(i),
                    owners.get(i),
                    names.get(i),
                    symbols.get(i),
                    supplies.get(i).longValue()
                )
            );
        }
        return result;
    }

    public static List<DFABalanceDTO> tupleToDFABalanceDTO(
        Tuple2<List<String>, List<BigInteger>> tuple
    ) {
        final var addresses = tuple.component1();
        final var balances = tuple.component2();
        int length = addresses.size();
        if (balances.size() != length) {
            throw new IncorrectEthereumResponseFormatException(
                "Arrays for dfa info list has different size!"
            );
        }

        final var result = new LinkedList<DFABalanceDTO>();
        for (int i = 0; i < length; i++) {
            final var balance = balances.get(i).longValue();
            if (balance > 0) {
                result.add(
                    new DFABalanceDTO(
                        addresses.get(i),
                        balance
                    )
                );
            }
        }
        return result;
    }

    public static List<ExchangeInfo> tupleToRequestInfoDTO(
        Tuple5<List<String>, List<BigInteger>, List<BigInteger>, List<BigInteger>, List<BigInteger>> tuple
    ) {
        final var users = tuple.component1();
        final var amountsToGet = tuple.component2();
        final var amountsToGive = tuple.component3();
        final var types = tuple.component4();
        final var indexes = tuple.component5();
        int length = users.size();
        if (amountsToGive.size() != length || amountsToGet.size() != length || types.size() != length || indexes.size() != length) {
            throw new IncorrectEthereumResponseFormatException(
                "Arrays for request info list has different size!"
            );
        }
        final var result = new LinkedList<ExchangeInfo>();
        for (int i = 0; i < length; i++) {
            long amountToGive = amountsToGive.get(i).longValue();
            long amountToGet = amountsToGet.get(i).longValue();
            ExchangeType type = ExchangeType.getByCode(types.get(i).intValue());
            long index = indexes.get(i).longValue();
            result.add(
                new ExchangeInfo(
                    users.get(i),
                    amountToGive,
                    amountToGet,
                    type,
                    index
                )
            );
        }
        return result;
    }
}
