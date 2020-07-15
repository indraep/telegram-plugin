package com.indraep.telegram.plugin.bot.model.request.markup;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ForceReplyMarkup implements ReplyMarkup {

  @JsonProperty("force_reply")
  private boolean forceReply = true;

  private boolean selective = true;

  public ForceReplyMarkup(boolean selective) {
    this.selective = selective;
  }

}
