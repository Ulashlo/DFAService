package com.hse.dfa.backend.configuration.web3j;

import com.hse.dfa.backend.properties.web3j.Web3jServerProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

@Configuration
@RequiredArgsConstructor
public class Web3jConfig {
    private final Web3jServerProperties serverProperties;

    @Bean
    public Web3j web3j() {
        return Web3j.build(new HttpService(serverProperties.getUrl()));
    }
}
