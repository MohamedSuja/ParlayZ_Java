package com.parlayZ.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:messages.properties")
public class MessageConfig {

    @Autowired
    private Environment env;

    public String getFullMessage(String code) {
        return env.getProperty(code);
    }

    public String getMessage(String code) {
        String errorMessage = env.getProperty(code);
        errorMessage = errorMessage.substring(errorMessage.indexOf(":") + 1).trim();
        return errorMessage;
    }

    public String getErrorCode(String code) {
        String errorMessage = env.getProperty(code);
        if (errorMessage != null) {
            errorMessage = errorMessage.substring(0, errorMessage.indexOf(":")).trim();
        } else {
            errorMessage = code;
        }

        return errorMessage;
    }
}
