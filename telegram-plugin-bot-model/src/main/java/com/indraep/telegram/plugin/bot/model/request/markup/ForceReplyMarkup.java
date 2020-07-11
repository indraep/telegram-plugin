package com.indraep.telegram.plugin.bot.model.request.markup;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ForceReplyMarkup implements ReplyMarkup {

  @JsonProperty("force_reply")
  private static final boolean forceReply = true;

  @Getter
  private boolean selective = true;

  public ForceReplyMarkup(boolean selective) {
    this.selective = selective;
  }

}
