package com.hse.dfa.backend.properties.web3j;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "web3j.server")
public class Web3jServerProperties {
    private String url;
}
