package com.indraep.telegram.plugin.bot.client;

import com.indraep.telegram.plugin.bot.model.request.EditMessageRequest;
import com.indraep.telegram.plugin.bot.model.request.SendMessageRequest;
import com.indraep.telegram.plugin.bot.model.response.SendMessageResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest(classes = TestApplication.class)
public class TelebotClientFallbackTest {

  @Autowired
  private TelebotClient telebotClient;

  private static final String BOT_TOKEN = "TOKEN";
  private static final long CHAT_ID = 321L;
  private static final long MESSAGE_ID = 321123L;

  @Test
  public void sendMessageTest() {
    StepVerifier.create(telebotClient.sendMessage(BOT_TOKEN, SendMessageRequest.builder(CHAT_ID, "send message test").build()))
        .assertNext(response -> {
          assertEquals(200, response.getStatusCodeValue());

          SendMessageResponse sendMessageResponse = response.getBody();
          assertFalse(sendMessageResponse.isOk());
          assertEquals("FALLBACK", sendMessageResponse.getDescription());
        })
        .verifyComplete();
  }

  @Test
  public void editMessageTest() {
    StepVerifier.create(telebotClient.editMessage(BOT_TOKEN, EditMessageRequest.builder(CHAT_ID, MESSAGE_ID, "edit message test").build()))
        .assertNext(response -> {
          assertEquals(200, response.getStatusCodeValue());

          SendMessageResponse sendMessageResponse = response.getBody();
          assertFalse(sendMessageResponse.isOk());
          assertEquals("FALLBACK", sendMessageResponse.getDescription());
        })
        .verifyComplete();
  }

}
