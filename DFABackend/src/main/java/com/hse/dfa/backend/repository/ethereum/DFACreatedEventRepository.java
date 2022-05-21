package com.hse.dfa.backend.repository.ethereum;

import com.hse.dfa.backend.model.ethereum.DFACreatedEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DFACreatedEventRepository extends JpaRepository<DFACreatedEvent, Long> {
    @Query("select max(e.blockNum) from DFACreatedEvent e")
    Optional<Long> getMaxBlockNum();
}

