package com.indraep.telegram.plugin.bot.model.response.nested.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.indraep.telegram.plugin.bot.model.response.nested.message.base.TelegramVisualFile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class TelegramDocumentMessage extends TelegramVisualFile {

  @JsonProperty("file_name")
  private String fileName;

}
