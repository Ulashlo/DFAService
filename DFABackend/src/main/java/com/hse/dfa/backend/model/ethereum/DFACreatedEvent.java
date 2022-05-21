package com.hse.dfa.backend.model.ethereum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "dfa_created_event", schema ="ethereum")
public class DFACreatedEvent {
    @Id
    @Getter
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private long id;

    @Getter
    @Column(name = "user_who_created_address")
    private String userWhoCreatedAddress;

    @Getter
    @Column(name = "dfa_address")
    private String dfaAddress;

    @Getter
    @Column(name = "dfa_name")
    private String dfaName;

    @Getter
    @Column(name = "symbol")
    private String symbol;

    @Getter
    @Column(name = "supply")
    private Long supply;

    @Getter
    @Column(name = "block_num")
    private Long blockNum;

    public DFACreatedEvent(String userWhoCreatedAddress, String dfaAddress, String dfaName, String symbol, Long supply, Long blockNum) {
        this.userWhoCreatedAddress = userWhoCreatedAddress;
        this.dfaAddress = dfaAddress;
        this.dfaName = dfaName;
        this.symbol = symbol;
        this.supply = supply;
        this.blockNum = blockNum;
    }
}
