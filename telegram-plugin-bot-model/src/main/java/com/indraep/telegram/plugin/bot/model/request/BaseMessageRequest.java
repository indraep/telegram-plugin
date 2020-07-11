package com.indraep.telegram.plugin.bot.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.indraep.telegram.plugin.bot.model.constants.ParseMode;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode
@SuperBuilder
@Getter
public class BaseMessageRequest {

  @JsonProperty("chat_id")
  private long chatId;

  private String text;

  @JsonProperty("parse_mode")
  @Builder.Default
  private ParseMode parseMode = ParseMode.Markdown;

  @JsonProperty("disable_web_page_preview")
  @Builder.Default
  private Boolean disableWebPagePreview = false;

}
