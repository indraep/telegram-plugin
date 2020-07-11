package com.indraep.telegram.plugin.bot.client;

import com.blibli.oss.backend.apiclient.annotation.ApiClient;
import com.indraep.telegram.plugin.bot.client.fallback.TelebotClientFallback;
import com.indraep.telegram.plugin.bot.model.request.EditMessageRequest;
import com.indraep.telegram.plugin.bot.model.request.SendMessageRequest;
import com.indraep.telegram.plugin.bot.model.response.SendMessageResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import reactor.core.publisher.Mono;

@ApiClient(
    name = "telebotClient",
    fallback = TelebotClientFallback.class
)
public interface TelebotClient {

  @RequestMapping(
      path = "/bot{botToken}/sendMessage",
      method = RequestMethod.POST,
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE
  )
  Mono<ResponseEntity<SendMessageResponse>> sendMessage(
      @PathVariable("botToken") String botToken,
      @RequestBody SendMessageRequest request
  );

  @RequestMapping(
      path = "/bot{botToken}/editMessageText",
      method = RequestMethod.POST,
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE
  )
  Mono<ResponseEntity<SendMessageResponse>> editMessage(
      @PathVariable("botToken") String botToken,
      @RequestBody EditMessageRequest request
  );

}