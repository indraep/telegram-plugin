package com.indraep.telegram.plugin.bot.client.service;

import com.indraep.telegram.plugin.bot.model.WebhookUpdate;
import com.indraep.telegram.plugin.bot.model.constants.UpdateType;
import com.indraep.telegram.plugin.bot.model.response.TelegramMessage;

import java.util.Optional;

public interface TelegramUpdateService {

  UpdateType getUpdateType(WebhookUpdate webhookUpdate);

  boolean isBotCommand(WebhookUpdate update);

  Optional<String> getBotCommandParam(WebhookUpdate update);

  Optional<String> getBotCommand(WebhookUpdate update);

  Optional<String> getInlineCallback(WebhookUpdate update);

  long getMessageId(WebhookUpdate update);

  long getSenderId(WebhookUpdate update);

  Optional<TelegramMessage> getMessage(WebhookUpdate update);

  Optional<Long> getRepliedMessageId(WebhookUpdate update);

}
