package com.indraep.telegram.plugin.bot.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.indraep.telegram.plugin.bot.model.request.markup.InlineKeyboardMarkup;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode
@Getter
@SuperBuilder(builderMethodName = "privateBuilder")
public class EditMessageRequest extends BaseMessageRequest {

  @JsonProperty("message_id")
  private long messageId;

  @JsonProperty("reply_markup")
  private InlineKeyboardMarkup replyMarkup;

  public static EditMessageRequest.EditMessageRequestBuilder builder(long chatId, long messageId, String text) {
    return privateBuilder()
        .chatId(chatId)
        .messageId(messageId)
        .text(text);
  }

}