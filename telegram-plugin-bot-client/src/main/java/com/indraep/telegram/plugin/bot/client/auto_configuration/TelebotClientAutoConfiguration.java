package com.indraep.telegram.plugin.bot.client.auto_configuration;

import com.indraep.telegram.plugin.bot.client.TelebotClient;
import com.indraep.telegram.plugin.bot.client.fallback.TelebotClientFallback;
import com.indraep.telegram.plugin.bot.client.properties.TelegramBotProperties;
import com.indraep.telegram.plugin.bot.client.service.TelebotService;
import com.indraep.telegram.plugin.bot.client.service.TelegramUpdateService;
import com.indraep.telegram.plugin.bot.client.service.impl.TelebotServiceImpl;
import com.indraep.telegram.plugin.bot.client.service.impl.TelegramUpdateServiceImpl;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(TelegramBotProperties.class)
public class TelebotClientAutoConfiguration {

  @Bean
  public TelebotClientFallback telebotClientFallback() {
    return new TelebotClientFallback();
  }

  @Bean
  public TelebotService telebotService(TelebotClient telebotClient, TelegramBotProperties telegramBotProperties) {
    return new TelebotServiceImpl(telebotClient, telegramBotProperties);
  }

  @Bean
  public TelegramUpdateService telegramUpdateService() {
    return new TelegramUpdateServiceImpl();
  }

}
