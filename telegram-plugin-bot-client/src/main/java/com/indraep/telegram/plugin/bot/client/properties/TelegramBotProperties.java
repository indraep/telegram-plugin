package com.indraep.telegram.plugin.bot.client.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("telegram.bot")
public class TelegramBotProperties {

  private String token;

}
