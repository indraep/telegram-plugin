package com.indraep.telegram.plugin.bot.client.service.impl;

import com.indraep.telegram.plugin.bot.client.TelebotClient;
import com.indraep.telegram.plugin.bot.client.properties.TelegramBotProperties;
import com.indraep.telegram.plugin.bot.client.service.TelebotService;
import com.indraep.telegram.plugin.bot.model.request.EditMessageRequest;
import com.indraep.telegram.plugin.bot.model.request.SendMessageRequest;
import com.indraep.telegram.plugin.bot.model.response.SendMessageResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

@Slf4j
public class TelebotServiceImpl implements TelebotService {

  private TelebotClient telebotClient;
  private TelegramBotProperties telegramBotProperties;

  public TelebotServiceImpl(TelebotClient telebotClient, TelegramBotProperties telegramBotProperties) {
    this.telebotClient = telebotClient;
    this.telegramBotProperties = telegramBotProperties;
  }

  public Mono<SendMessageResponse> sendMessage(SendMessageRequest sendMessageRequest) {
    return telebotClient.sendMessage(telegramBotProperties.getToken(), sendMessageRequest)
        .doOnSuccess(response -> {
          if (response.getStatusCodeValue() != HttpStatus.OK.value()) {
            log.warn("getting response code: {}, when send message. response: {}", response.getStatusCodeValue(), response);
          }
        })
        .map(ResponseEntity::getBody);
  }

  public Mono<SendMessageResponse> editMessage(EditMessageRequest editMessageRequest) {
    return telebotClient.editMessage(telegramBotProperties.getToken(), editMessageRequest)
        .doOnSuccess(response -> {
          if (response.getStatusCodeValue() != HttpStatus.OK.value()) {
            log.warn("getting response code: {}, when edit message. response: {}", response.getStatusCodeValue(), response);
          }
        })
        .map(ResponseEntity::getBody);
  }

}
