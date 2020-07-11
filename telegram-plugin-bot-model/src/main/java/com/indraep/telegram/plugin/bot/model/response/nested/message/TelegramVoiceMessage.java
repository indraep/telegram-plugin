package com.indraep.telegram.plugin.bot.model.response.nested.message;

import com.indraep.telegram.plugin.bot.model.response.nested.message.base.TelegramFile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class TelegramVoiceMessage extends TelegramFile {

  private int duration;

}
