package com.indraep.telegram.plugin.bot.client.fallback;

import com.indraep.telegram.plugin.bot.client.TelebotClient;
import com.indraep.telegram.plugin.bot.model.request.EditMessageRequest;
import com.indraep.telegram.plugin.bot.model.request.SendMessageRequest;
import com.indraep.telegram.plugin.bot.model.response.SendMessageResponse;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

import java.util.Optional;

public class TelebotClientFallback implements TelebotClient {

  private static final SendMessageResponse FALLBACK_RESPONSE =
      SendMessageResponse.builder()
          .ok(false)
          .description("FALLBACK")
          .build();

  @Override
  public Mono<ResponseEntity<SendMessageResponse>> sendMessage(String botToken, SendMessageRequest request) {
    return Mono.fromCallable(() -> ResponseEntity.of(Optional.of(FALLBACK_RESPONSE)));
  }

  @Override
  public Mono<ResponseEntity<SendMessageResponse>> editMessage(String botToken, EditMessageRequest request) {
    return Mono.fromCallable(() -> ResponseEntity.of(Optional.of(FALLBACK_RESPONSE)));
  }
}
