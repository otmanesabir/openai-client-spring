package com.sabirotmane.service;

import com.sabirotmane.domain.CompletionMessage;
import com.sabirotmane.domain.CompletionRequest;
import com.sabirotmane.domain.CompletionResponse;
import java.util.List;
import lombok.NonNull;

public interface GPTService {

    @NonNull
    CompletionResponse completion(@NonNull CompletionRequest request);

    @NonNull
    CompletionResponse completion(@NonNull List<CompletionMessage> messages);

}
