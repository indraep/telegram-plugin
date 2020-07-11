package com.indraep.telegram.plugin.bot.model.request.markup.inline_keyboard;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.indraep.telegram.plugin.bot.model.exception.InlineCallbackException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder(builderMethodName = "privateBuilder")
@Getter
@EqualsAndHashCode
public class InlineKeyboardButton {

  private static final int MAX_CALLBACK_SIZE = 64;

  private String text;

  @JsonProperty("callback_data")
  private String callbackData;

  private String url;

  public InlineKeyboardButton(String text, String callbackData) {
    this.text = text;
    this.callbackData = callbackData;
  }

  public static InlineKeyboardButtonBuilder builder(String text, String callbackData) {
    return privateBuilder()
        .text(text)
        .callbackData(callbackData);
  }

}
