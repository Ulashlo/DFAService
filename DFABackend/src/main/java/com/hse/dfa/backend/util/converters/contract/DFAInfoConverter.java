package com.hse.dfa.backend.util.converters.contract;

import com.hse.dfa.backend.controller.dto.dfa.DFABalanceDTO;
import com.hse.dfa.backend.controller.dto.dfa.DFAViewDTO;
import com.hse.dfa.backend.exceptions.contract.IncorrectEthereumResponseFormatException;
import lombok.NoArgsConstructor;
import org.web3j.tuples.generated.Tuple2;
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
}
