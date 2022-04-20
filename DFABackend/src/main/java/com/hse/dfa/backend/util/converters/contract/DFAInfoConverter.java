package com.hse.dfa.backend.util.converters.contract;

import com.hse.dfa.backend.controller.dto.dfa.DFAViewDto;
import com.hse.dfa.backend.exceptions.contract.IncorrectEthereumResponseFormatException;
import lombok.NoArgsConstructor;
import org.web3j.tuples.generated.Tuple5;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class DFAInfoConverter {
    public static List<DFAViewDto> tupleToDFAViewDto(
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
        final var result = new LinkedList<DFAViewDto>();
        for (int i = 0; i < length; i++) {
            result.add(
                new DFAViewDto(
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
}
