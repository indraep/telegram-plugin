package com.indraep.telegram.plugin.bot.model.response.nested;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.indraep.telegram.plugin.bot.model.response.TelegramUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TelegramChosenInlineResult {

  @JsonProperty("result_id")
  private String resultId;

  private TelegramUser from;

  @JsonProperty("inline_message_id")
  private String inlineMessageId;

  private String query;

}
