package com.hse.dfa.backend.repository.ethereum;

import com.hse.dfa.backend.model.ethereum.ExchangeCompletedEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExchangeCompletedEventRepository extends JpaRepository<ExchangeCompletedEvent, Long> {
    @Query("select max(e.blockNum) from ExchangeCompletedEvent e where e.firstDfaAddress = :dfa")
    Optional<Long> getMaxBlockNum(@Param("dfa") String dfa);

    List<ExchangeCompletedEvent> findAllByFirstUserAddressOrSecondUserAddress(String firstUserAddress, String secondUserAddress);

    List<ExchangeCompletedEvent> findAllByFirstDfaAddressOrSecondDfaAddress(String firstDfaAddress, String secondDfaAddress);
}
