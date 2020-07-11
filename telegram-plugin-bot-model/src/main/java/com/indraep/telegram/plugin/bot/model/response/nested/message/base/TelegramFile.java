package com.indraep.telegram.plugin.bot.model.response.nested.message.base;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class TelegramFile {

  @JsonProperty("file_id")
  private String fileId;

  @JsonProperty("file_unique_id")
  private String fileUniqueId;

  @JsonProperty("mime_type")
  private String mimeType;

  @JsonProperty("file_size")
  private long fileSize;

}
