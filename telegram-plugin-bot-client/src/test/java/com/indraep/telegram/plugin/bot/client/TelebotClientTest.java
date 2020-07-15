package com.indraep.telegram.plugin.bot.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.indraep.telegram.plugin.bot.model.request.EditMessageRequest;
import com.indraep.telegram.plugin.bot.model.request.SendMessageRequest;
import com.indraep.telegram.plugin.bot.model.request.markup.ForceReplyMarkup;
import com.indraep.telegram.plugin.bot.model.request.markup.InlineKeyboardMarkup;
import com.indraep.telegram.plugin.bot.model.request.markup.inline_keyboard.InlineKeyboardButton;
import com.indraep.telegram.plugin.bot.model.response.SendMessageResponse;
import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.io.IOException;
import java.util.Arrays;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.containing;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = TestApplication.class)
public class TelebotClientTest {

  @Autowired
  private TelebotClient telebotClient;

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private ResourceLoader resourceLoader;

  private static WireMockServer wireMockServer;

  private static final long CHAT_ID = 123456L;
  private static final long MESSAGE_ID = 123456789L;
  private static final String BOT_TOKEN = "TOKEN";

  @BeforeAll
  public static void beforeAll() {
    wireMockServer = new WireMockServer(8089);
    wireMockServer.start();
  }

  @AfterAll
  public static void afterAll() {
    wireMockServer.stop();
  }

  private String getResource(String path) throws IOException {
    return IOUtils.toString(
        resourceLoader.getResource("classpath:" + path).getInputStream()
    );
  }

  private void mockServer(String path, String requestBody, String responseBodyPath) throws IOException {
    wireMockServer.stubFor(
        post(urlPathEqualTo("/bot" + BOT_TOKEN + "/" + path))
            .withHeader(HttpHeaders.ACCEPT, equalTo(MediaType.APPLICATION_JSON_VALUE))
            .withHeader(HttpHeaders.CONTENT_TYPE, equalTo(MediaType.APPLICATION_JSON_VALUE))
            .withRequestBody(equalTo(requestBody))
            .willReturn(
                aResponse()
                    .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .withBody(getResource(responseBodyPath))
            )
    );
  }

  @Test
  @SneakyThrows
  public void sendMessageTest() {
    InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton("inline 1", "wawa1");
    InlineKeyboardMarkup markup = new InlineKeyboardMarkup(Arrays.asList(Arrays.asList(inlineKeyboardButton)));

    SendMessageRequest request =
        SendMessageRequest.builder(CHAT_ID, "send message test")
            .replyMarkup(markup)
            .build();

    mockServer("sendMessage", objectMapper.writeValueAsString(request), "/response/send_message.json");

    Mono<ResponseEntity<SendMessageResponse>> responseMono =
        telebotClient.sendMessage(BOT_TOKEN, request);

    StepVerifier.create(responseMono)
        .assertNext(response -> {
          assertEquals(200, response.getStatusCodeValue());

          SendMessageResponse sendMessageResponse = response.getBody();
          assertTrue(sendMessageResponse.isOk());
          assertEquals(654321L, sendMessageResponse.getResult().getMessageId());
        })
        .verifyComplete();
  }

  @Test
  @SneakyThrows
  public void sendMessageForceReplyTest() {
    SendMessageRequest request =
        SendMessageRequest.builder(CHAT_ID, "send message test")
            .replyMarkup(new ForceReplyMarkup(true))
            .build();

    wireMockServer.stubFor(
        post(urlPathEqualTo("/bot" + BOT_TOKEN + "/sendMessage"))
            .withHeader(HttpHeaders.ACCEPT, equalTo(MediaType.APPLICATION_JSON_VALUE))
            .withHeader(HttpHeaders.CONTENT_TYPE, equalTo(MediaType.APPLICATION_JSON_VALUE))
            .withRequestBody(containing("\"selective\":true"))
            .withRequestBody(containing("\"force_reply\":true"))
            .willReturn(
                aResponse()
                    .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .withBody(getResource("/response/send_message.json"))
            )
    );

    Mono<ResponseEntity<SendMessageResponse>> responseMono =
        telebotClient.sendMessage(BOT_TOKEN, request);

    StepVerifier.create(responseMono)
        .assertNext(response -> {
          assertEquals(200, response.getStatusCodeValue());

          SendMessageResponse sendMessageResponse = response.getBody();
          assertTrue(sendMessageResponse.isOk());
          assertEquals(654321L, sendMessageResponse.getResult().getMessageId());
        })
        .verifyComplete();
  }

  @Test
  @SneakyThrows
  public void editMessageTest() {
    InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton("inline 1", "wawa1");
    InlineKeyboardMarkup markup = new InlineKeyboardMarkup(Arrays.asList(Arrays.asList(inlineKeyboardButton)));

    EditMessageRequest request =
        EditMessageRequest.builder(CHAT_ID, MESSAGE_ID, "edit message test")
            .replyMarkup(markup)
            .build();

    mockServer("editMessageText", objectMapper.writeValueAsString(request), "/response/send_message.json");

    Mono<ResponseEntity<SendMessageResponse>> responseMono =
        telebotClient.editMessage(BOT_TOKEN, request);

    StepVerifier.create(responseMono)
        .assertNext(response -> {
          assertEquals(200, response.getStatusCodeValue());

          SendMessageResponse sendMessageResponse = response.getBody();
          assertTrue(sendMessageResponse.isOk());
          assertEquals(654321L, sendMessageResponse.getResult().getMessageId());
        })
        .verifyComplete();
  }

}
