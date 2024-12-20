package com.sabirotmane.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.sabirotmane.client.OpenAIClient;
import com.sabirotmane.config.OpenAIConfig;
import com.sabirotmane.domain.CompletionMessage;
import com.sabirotmane.domain.CompletionRequest;
import com.sabirotmane.domain.Role;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GPTServiceImplTest {

    @InjectMocks
    private GPTServiceImpl underTest;

    @Mock
    private OpenAIClient openAIClient;

    @Mock
    private OpenAIConfig config;

    @Captor
    private ArgumentCaptor<CompletionRequest> requestCaptor;

    @Test
    void completion() {
        CompletionRequest request = new CompletionRequest();
        request.setTemperature(1.0);
        request.setModel("gpt-4");

        underTest.completion(request);

        verify(openAIClient).create(request);
    }

    @Test
    void completion_shouldSetDefaults_whenNotProvidedInRequest() {
        CompletionMessage message = new CompletionMessage();
        message.setContent("Hello, world!");
        message.setRole(Role.system);
        List<CompletionMessage> messages = List.of(message);
        when(config.getTemperature()).thenReturn(0.8);
        when(config.getModel()).thenReturn("gpt-4");

        underTest.completion(messages);

        verify(openAIClient).create(requestCaptor.capture());
        CompletionRequest request = requestCaptor.getValue();
        assertThat(request).isNotNull();
        assertThat(request.getTemperature()).isEqualTo(0.8);
        assertThat(request.getModel()).isEqualTo("gpt-4");
    }
}
