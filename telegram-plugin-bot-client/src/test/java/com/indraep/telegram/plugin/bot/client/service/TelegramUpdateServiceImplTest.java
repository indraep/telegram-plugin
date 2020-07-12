package com.indraep.telegram.plugin.bot.client.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.indraep.telegram.plugin.bot.client.TestApplication;
import com.indraep.telegram.plugin.bot.model.WebhookUpdate;
import com.indraep.telegram.plugin.bot.model.constants.UpdateType;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = TestApplication.class)
public class TelegramUpdateServiceImplTest {

  @Autowired
  private TelegramUpdateService telegramUpdateService;

  @Autowired
  private ResourceLoader resourceLoader;

  @Autowired
  private ObjectMapper objectMapper;

  private WebhookUpdate newMessage;
  private WebhookUpdate replyMessage;
  private WebhookUpdate newMessageBotCommand;
  private WebhookUpdate newMessageBotCommandWithoutParam;
  private WebhookUpdate forwardMessage;
  private WebhookUpdate editMessage;
  private WebhookUpdate inlineCallback;

  @BeforeEach
  public void beforeEach() throws IOException {
    newMessage = get("/webhook/new_message.json");
    replyMessage = get("/webhook/new_reply_message.json");
    newMessageBotCommand = get("/webhook/new_message_bot_command.json");
    newMessageBotCommandWithoutParam = get("/webhook/new_message_bot_command_without_param.json");
    forwardMessage = get("/webhook/forward_message_with_bot_command.json");
    editMessage = get("/webhook/edit_message.json");
    inlineCallback = get("/webhook/inline_callback.json");
  }

  private WebhookUpdate get(String path) throws IOException {
    String data = IOUtils.toString(
        resourceLoader.getResource("classpath:" + path)
            .getInputStream()
    );

    return objectMapper.readValue(data, WebhookUpdate.class);
  }

  @Test
  public void getUpdateTypeTest() {
    assertEquals(UpdateType.NEW_MESSAGE, telegramUpdateService.getUpdateType(newMessage));
    assertEquals(UpdateType.REPLY_MESSAGE, telegramUpdateService.getUpdateType(replyMessage));
    assertEquals(UpdateType.EDIT_MESSAGE, telegramUpdateService.getUpdateType(editMessage));
    assertEquals(UpdateType.FORWARD_MESSAGE, telegramUpdateService.getUpdateType(forwardMessage));
    assertEquals(UpdateType.INLINE_CALLBACK, telegramUpdateService.getUpdateType(inlineCallback));
  }

  @Test
  public void getUpdateTypeNotSupported() {
    assertThrows(
        UnsupportedOperationException.class,
        () -> telegramUpdateService.getUpdateType(new WebhookUpdate())
    );
  }

  @Test
  public void getBotCommandParamTest() {
    Optional<String> result = telegramUpdateService.getBotCommandParam(newMessageBotCommand);

    assertEquals("ini command bot tanpa nama", result.get());
  }

  @Test
  public void getBotCommandParamButWithoutParamTest() {
    Optional<String> result = telegramUpdateService.getBotCommandParam(newMessageBotCommandWithoutParam);

    assertFalse(result.isPresent());
  }

  @Test
  public void isBotCommandTest() {
    assertTrue(telegramUpdateService.isBotCommand(newMessageBotCommandWithoutParam));
    assertTrue(telegramUpdateService.isBotCommand(newMessageBotCommand));
    assertTrue(telegramUpdateService.isBotCommand(forwardMessage));

    assertFalse(telegramUpdateService.isBotCommand(newMessage));
    assertFalse(telegramUpdateService.isBotCommand(editMessage));
    assertFalse(telegramUpdateService.isBotCommand(inlineCallback));
  }

  @Test
  public void getBotCommandTest() {
    assertEquals("/add_todo", telegramUpdateService.getBotCommand(newMessageBotCommandWithoutParam).get());
    assertEquals("/add_todo", telegramUpdateService.getBotCommand(newMessageBotCommand).get());
    assertEquals("/coba", telegramUpdateService.getBotCommand(forwardMessage).get());

    assertFalse(telegramUpdateService.getBotCommand(newMessage).isPresent());
    assertFalse(telegramUpdateService.getBotCommand(editMessage).isPresent());
    assertFalse(telegramUpdateService.getBotCommand(inlineCallback).isPresent());
  }

  @Test
  public void getInlineCallbackTest() {
    assertEquals("inline callback data", telegramUpdateService.getInlineCallback(inlineCallback).get());

    assertFalse(telegramUpdateService.getInlineCallback(newMessage).isPresent());
    assertFalse(telegramUpdateService.getInlineCallback(forwardMessage).isPresent());
  }

  @Test
  public void getChatIdTest() {
    assertEquals(910L, telegramUpdateService.getChatId(newMessage));
    assertEquals(92L, telegramUpdateService.getChatId(replyMessage));
    assertEquals(-21L, telegramUpdateService.getChatId(editMessage));
    assertEquals(-9231L, telegramUpdateService.getChatId(forwardMessage));
    assertEquals(321L, telegramUpdateService.getChatId(inlineCallback));
  }

  @Test
  public void getMessageIdTest() {
    assertEquals(144L, telegramUpdateService.getMessageId(newMessage));
    assertEquals(153L, telegramUpdateService.getMessageId(replyMessage));
    assertEquals(148L, telegramUpdateService.getMessageId(editMessage));
    assertEquals(152L, telegramUpdateService.getMessageId(forwardMessage));
    assertEquals(141L, telegramUpdateService.getMessageId(inlineCallback));
  }

  @Test
  public void getSenderId() {
    assertEquals(12L, telegramUpdateService.getSenderId(newMessage));
    assertEquals(999L, telegramUpdateService.getSenderId(replyMessage));
    assertEquals(21L, telegramUpdateService.getSenderId(editMessage));
    assertEquals(321L, telegramUpdateService.getSenderId(forwardMessage));
    assertEquals(987L, telegramUpdateService.getSenderId(inlineCallback));
  }

  @Test
  public void getRepliedMessageIdTest() {
    assertEquals(142L, telegramUpdateService.getRepliedMessageId(replyMessage).get());

    assertFalse(telegramUpdateService.getRepliedMessageId(newMessage).isPresent());
    assertFalse(telegramUpdateService.getRepliedMessageId(forwardMessage).isPresent());
    assertFalse(telegramUpdateService.getRepliedMessageId(inlineCallback).isPresent());
  }

}
