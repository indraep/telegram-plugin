package com.indraep.telegram.plugin.bot.model.response.nested.message.base;

import com.indraep.telegram.plugin.bot.model.response.nested.message.TelegramPhotoSize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class TelegramVisualFile extends TelegramFile {

  private TelegramPhotoSize thumb;

}
