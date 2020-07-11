package com.indraep.telegram.plugin.bot.client.service;

import com.indraep.telegram.plugin.bot.model.request.EditMessageRequest;
import com.indraep.telegram.plugin.bot.model.request.SendMessageRequest;
import com.indraep.telegram.plugin.bot.model.response.SendMessageResponse;
import reactor.core.publisher.Mono;

public interface TelebotService {

  Mono<SendMessageResponse> sendMessage(SendMessageRequest sendMessageRequest);

  Mono<SendMessageResponse> editMessage(EditMessageRequest editMessageRequest);

}
