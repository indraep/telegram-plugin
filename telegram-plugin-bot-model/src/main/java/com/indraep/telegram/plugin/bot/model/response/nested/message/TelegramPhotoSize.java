package com.indraep.telegram.plugin.bot.model.response.nested.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TelegramPhotoSize {

  @JsonProperty("file_id")
  private String fileId;

  @JsonProperty("file_unique_id")
  private String fileUniqueId;

  private int width;

  private int height;

  @JsonProperty("file_size")
  private long fileSize;

}
