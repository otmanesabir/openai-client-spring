package com.sabirotmane.service.impl;

import com.sabirotmane.client.OpenAIClient;
import com.sabirotmane.config.OpenAIConfig;
import com.sabirotmane.domain.CompletionMessage;
import com.sabirotmane.domain.CompletionRequest;
import com.sabirotmane.domain.CompletionResponse;
import com.sabirotmane.service.GPTService;
import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class GPTServiceImpl implements GPTService {

    private final OpenAIClient gptClient;
    private final OpenAIConfig config;

    @Override
    public @NonNull CompletionResponse completion(@NonNull CompletionRequest request) {
        return gptClient.create(request);
    }

    @Override
    public @NonNull CompletionResponse completion(@NonNull List<CompletionMessage> messages) {
        CompletionRequest request = createRequestWithDefaults(messages);
        return gptClient.create(request);
    }

    private CompletionRequest createRequestWithDefaults(List<CompletionMessage> messages) {
        CompletionRequest request = new CompletionRequest();
        request.setMessages(messages);
        request.setModel(config.getModel());
        request.setTemperature(config.getTemperature());
        return request;
    }
}
