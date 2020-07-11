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
public class TelegramAudioMesssage extends TelegramVisualFile {

  private int duration;

  private String performer;

  private String title;

}
