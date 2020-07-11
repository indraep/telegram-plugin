package com.indraep.telegram.plugin.bot.client.service.impl;

import com.indraep.telegram.plugin.bot.client.service.TelegramUpdateService;
import com.indraep.telegram.plugin.bot.model.WebhookUpdate;
import com.indraep.telegram.plugin.bot.model.constants.MessageEntityType;
import com.indraep.telegram.plugin.bot.model.constants.UpdateType;
import com.indraep.telegram.plugin.bot.model.response.TelegramMessage;
import com.indraep.telegram.plugin.bot.model.response.TelegramUser;
import com.indraep.telegram.plugin.bot.model.response.nested.TelegramCallbackQuery;
import com.indraep.telegram.plugin.bot.model.response.nested.message.TelegramMessageEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Optional;

public class TelegramUpdateServiceImpl implements TelegramUpdateService {

  @Override
  public UpdateType getUpdateType(WebhookUpdate webhookUpdate) {
    if (webhookUpdate.getMessage() != null &&
        webhookUpdate.getMessage().getForwardFrom() == null &&
        webhookUpdate.getMessage().getReplyToMessage() == null) {
      return UpdateType.NEW_MESSAGE;
    } else if (webhookUpdate.getMessage() != null &&
        webhookUpdate.getMessage().getReplyToMessage() != null) {
      return UpdateType.REPLY_MESSAGE;
    } else if (webhookUpdate.getMessage() != null && webhookUpdate.getMessage().getForwardFrom() != null) {
      return UpdateType.FORWARD_MESSAGE;
    } else if (webhookUpdate.getEditedMessage() != null) {
      return UpdateType.EDIT_MESSAGE;
    } else if (webhookUpdate.getCallbackQuery() != null) {
      return UpdateType.INLINE_CALLBACK;
    } else {
      throw new UnsupportedOperationException("Update Type not supported yet: " + webhookUpdate);
    }
  }

  @Override
  public Optional<String> getBotCommandParam(WebhookUpdate update) {
    return Optional.ofNullable(update.getMessage())
        .filter(this::hasBotCommand)
        .map(message ->
            message.getText()
                .substring(message.getEntities().get(0).getLength())
        )
        .filter(param -> !StringUtils.isEmpty(param))
        .map(param -> param.trim());
  }

  @Override
  public boolean isBotCommand(WebhookUpdate update) {
    if (update.getMessage() != null) {
      TelegramMessage message = getMessage(update).get();

      return hasBotCommand(message);
    }

    return false;
  }

  @Override
  public Optional<String> getBotCommand(WebhookUpdate update) {
    return Optional.ofNullable(update.getMessage())
        .filter(this::hasBotCommand)
        .map(TelegramMessage::getEntities)
        .filter(entities -> !CollectionUtils.isEmpty(entities))
        .map(entities -> entities.get(0))
        .map(commandEntity -> extractMessageEntity(update.getMessage(), commandEntity))
        .flatMap(command -> getBotCommand(command));
  }

  @Override
  public Optional<String> getInlineCallback(WebhookUpdate update) {
    return Optional.ofNullable(update.getCallbackQuery())
        .map(TelegramCallbackQuery::getData);
  }

  @Override
  public long getMessageId(WebhookUpdate update) {
    return getMessage(update)
        .map(TelegramMessage::getMessageId)
        .orElseThrow(() -> new RuntimeException("unsupported message"));
  }

  @Override
  public long getSenderId(WebhookUpdate update) {
    UpdateType updateType = getUpdateType(update);

    if (UpdateType.INLINE_CALLBACK.equals(updateType)) {
      return Optional.ofNullable(update.getCallbackQuery())
          .map(TelegramCallbackQuery::getFrom)
          .map(TelegramUser::getId)
          .get();
    }

    return getMessage(update)
        .map(TelegramMessage::getFrom)
        .map(TelegramUser::getId)
        .orElseThrow(() -> new RuntimeException("unsupported message"));
  }

  @Override
  public Optional<TelegramMessage> getMessage(WebhookUpdate update) {
    if (update.getMessage() != null) {
      return Optional.of(update.getMessage());
    } else if (update.getEditedMessage() != null) {
      return Optional.of(update.getEditedMessage());
      // TODO:not supported yet
//    } else if (update.getChannelPost() != null) {
//      return Optional.of(update.getChannelPost());
//    } else if (update.getEditedChannelPost() != null) {
//      return Optional.of(update.getEditedChannelPost());
    } else if (update.getCallbackQuery() != null && update.getCallbackQuery().getMessage() != null) {
      return Optional.of(update.getCallbackQuery().getMessage());
    } else {
      return Optional.empty();
    }
  }

  @Override
  public Optional<Long> getRepliedMessageId(WebhookUpdate update) {
    return Optional.ofNullable(update.getMessage())
        .map(TelegramMessage::getReplyToMessage)
        .map(TelegramMessage::getMessageId);
  }

  private boolean hasBotCommand(TelegramMessage message) {
    return !CollectionUtils.isEmpty(message.getEntities()) &&
        MessageEntityType.bot_command.name().equals(message.getEntities().get(0).getType());
  }

  private Optional<String> getBotCommand(String commandEntity) {
    return Optional.ofNullable(commandEntity)
        .map(command -> command.split("@"))
        .filter(terms -> terms.length > 0)
        .map(terms -> terms[0]);
  }

  private String extractMessageEntity(TelegramMessage message, TelegramMessageEntity entity) {
    return message.getText()
        .substring(entity.getOffset(), entity.getOffset() + entity.getLength());
  }
}
