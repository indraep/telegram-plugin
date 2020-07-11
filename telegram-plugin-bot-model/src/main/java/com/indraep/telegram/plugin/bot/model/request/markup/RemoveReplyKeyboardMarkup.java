package com.indraep.telegram.plugin.bot.model.request.markup;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class RemoveReplyKeyboardMarkup implements ReplyMarkup {

  @JsonProperty("remove_keyboard")
  private static final boolean removeKeyboard = true;

  @Getter
  private boolean selective = true;

  public RemoveReplyKeyboardMarkup(boolean selective) {
    this.selective = selective;
  }

}
