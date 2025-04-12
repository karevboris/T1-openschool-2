package com.openschool.hw.starter.config;

import com.openschool.hw.starter.aspect.LogAspect;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(LogProperties.class)
@ConditionalOnProperty(name = "log.enabled", havingValue = "true", matchIfMissing = true)
public class LogAutoConfiguration {

    @Bean
    public LogAspect logAspect(LogProperties logProperties) {
        return new LogAspect(logProperties);
    }
}
