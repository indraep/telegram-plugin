package com.indraep.telegram.plugin.bot.model.response.nested.message;

import com.indraep.telegram.plugin.bot.model.response.TelegramUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TelegramMessageEntity {

  private String type;

  private int offset;

  private int length;

  private String url;

  private TelegramUser user;

  private String language;

}
