package com.hse.dfa.backend.model.ethereum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "exchange_completed_event", schema ="ethereum")
public class ExchangeCompletedEvent {
    @Id
    @Getter
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private long id;

    @Getter
    @Column(name = "first_user_address")
    private String firstUserAddress;

    @Getter
    @Column(name = "first_dfa_address")
    private String firstDfaAddress;

    @Getter
    @Column(name = "first_amount")
    private Long firstAmount;

    @Getter
    @Column(name = "second_user_address")
    private String secondUserAddress;

    @Getter
    @Column(name = "second_dfa_address")
    private String secondDfaAddress;

    @Getter
    @Column(name = "second_amount")
    private Long secondAmount;

    @Getter
    @Column(name = "block_num")
    private Long blockNum;

    public ExchangeCompletedEvent(String firstUserAddress, String firstDfaAddress, Long firstAmount, String secondUserAddress, String secondDfaAddress, Long secondAmount, Long blockNum) {
        this.firstUserAddress = firstUserAddress;
        this.firstDfaAddress = firstDfaAddress;
        this.firstAmount = firstAmount;
        this.secondUserAddress = secondUserAddress;
        this.secondDfaAddress = secondDfaAddress;
        this.secondAmount = secondAmount;
        this.blockNum = blockNum;
    }
}
