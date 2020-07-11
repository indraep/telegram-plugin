package com.indraep.telegram.plugin.bot.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.indraep.telegram.plugin.bot.model.request.markup.ReplyMarkup;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode
@Getter
@SuperBuilder(builderMethodName = "privateBuilder")
public class SendMessageRequest extends BaseMessageRequest {

  @JsonProperty("reply_to_message_id")
  private Integer replyToMessageId;

  @JsonProperty("disable_notification")
  @Builder.Default
  private Boolean disableNotification = false;

  @JsonProperty("reply_markup")
  private ReplyMarkup replyMarkup;

  public static SendMessageRequestBuilder builder(long chatId, String text) {
    return privateBuilder()
        .chatId(chatId)
        .text(text);
  }

}