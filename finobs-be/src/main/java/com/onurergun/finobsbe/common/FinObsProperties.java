package com.onurergun.finobsbe.common;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@ConfigurationProperties(prefix = "finobs")
@Configuration("finObsProperties")
@Data
public class FinObsProperties {
    private String apiPrefix;
    private String jwtKey;
}
