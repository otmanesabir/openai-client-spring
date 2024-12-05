package com.sabirotmane.config;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.sabirotmane.domain.CompletionRequest;
import com.sabirotmane.domain.CompletionRequestSerializer;
import com.sabirotmane.parser.service.GPTFunctionParser;
import com.sabirotmane.service.TokenizationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class JacksonConfig {

    @Bean
    public SimpleModule completionRequestSerializer(OpenAIConfig config, TokenizationService tokenizationService, GPTFunctionParser functionParser) {
        SimpleModule module = new SimpleModule();
        module.addSerializer(CompletionRequest.class, new CompletionRequestSerializer(functionParser, tokenizationService, config));
        return module;
    }
}
