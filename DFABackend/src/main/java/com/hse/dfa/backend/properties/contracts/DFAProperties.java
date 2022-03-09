package com.hse.dfa.backend.properties.contracts;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "contracts.dfa")
public class DFAProperties {
    private String address;
}
