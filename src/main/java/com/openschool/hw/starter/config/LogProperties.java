package com.openschool.hw.starter.config;

import org.slf4j.event.Level;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

@ConfigurationProperties(prefix = "log")
public class LogProperties {
    private final Level level;
    private final Boolean enabled;

    @ConstructorBinding
    public LogProperties(Level level, Boolean enabled) {
        this.level = level == null ? Level.INFO : level;
        this.enabled = enabled;
    }

    public Level getLevel() {
        return level;
    }

    public Boolean getEnabled() {
        return enabled;
    }
}
