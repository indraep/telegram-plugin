package com.indraep.telegram.plugin.bot.model.response.nested.message;

import com.indraep.telegram.plugin.bot.model.response.nested.message.base.TelegramVisualFile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class TelegramVideoMessage extends TelegramVisualFile {

  private int width;

  private int height;

  private int duration;

}
