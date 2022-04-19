package com.hse.dfa.backend.properties.contracts;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "contracts.factory")
public class FactoryProperties {
    private String address;
}
