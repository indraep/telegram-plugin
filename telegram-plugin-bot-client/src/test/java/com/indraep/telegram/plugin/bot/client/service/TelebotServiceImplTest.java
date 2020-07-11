package com.indraep.telegram.plugin.bot.client.service;

import com.indraep.telegram.plugin.bot.client.TelebotClient;
import com.indraep.telegram.plugin.bot.client.TestApplication;
import com.indraep.telegram.plugin.bot.model.request.EditMessageRequest;
import com.indraep.telegram.plugin.bot.model.request.SendMessageRequest;
import com.indraep.telegram.plugin.bot.model.response.SendMessageResponse;
import com.indraep.telegram.plugin.bot.model.response.TelegramMessage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.when;

@SpringBootTest(classes = TestApplication.class)
public class TelebotServiceImplTest {

  @Autowired
  private TelebotService telebotService;

  @MockBean
  private TelebotClient telebotClient;

  @Test
  public void sendMessageSuccess() {
    mockSendMessage(HttpStatus.OK);

    StepVerifier.create(telebotService.sendMessage(constructSendRequest()))
        .expectNext(constructResponse())
        .verifyComplete();
  }

  @Test
  public void editMessageSuccess() {
    mockEditMessage(HttpStatus.OK);

    StepVerifier.create(telebotService.editMessage(constructEditRequest()))
        .expectNext(constructResponse())
        .verifyComplete();
  }

  @Test
  public void sendMessageError() {
    mockSendMessage(HttpStatus.BAD_REQUEST);

    StepVerifier.create(telebotService.sendMessage(constructSendRequest()))
        .expectNext(constructResponse())
        .verifyComplete();
  }

  @Test
  public void editMessageError() {
    mockEditMessage(HttpStatus.INTERNAL_SERVER_ERROR);

    StepVerifier.create(telebotService.editMessage(constructEditRequest()))
        .expectNext(constructResponse())
        .verifyComplete();
  }

  private void mockSendMessage(HttpStatus httpStatus) {
    ResponseEntity<SendMessageResponse> response =
        new ResponseEntity<>(constructResponse(), httpStatus);

    when(telebotClient.sendMessage("EXTOKEN", constructSendRequest())).
        thenReturn(Mono.just(response));
  }

  private void mockEditMessage(HttpStatus httpStatus) {
    ResponseEntity<SendMessageResponse> response =
        new ResponseEntity<>(constructResponse(), httpStatus);

    when(telebotClient.editMessage("EXTOKEN", constructEditRequest())).
        thenReturn(Mono.just(response));
  }

  private SendMessageResponse constructResponse() {
    return SendMessageResponse.builder()
        .ok(true)
        .result(
            TelegramMessage.builder()
                .messageId(654123)
                .build()
        )
        .build();
  }

  private SendMessageRequest constructSendRequest() {
    return SendMessageRequest.builder(123, "send")
        .build();
  }

  private EditMessageRequest constructEditRequest() {
    return EditMessageRequest.builder(123, 321, "edit")
        .build();
  }

}
