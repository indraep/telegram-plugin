package com.indraep.telegram.plugin.bot.model.constants;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public enum ChatType {

  PRIVATE, GROUP, SUPERGROUP, CHANNEL, UNKNOWN;

  static ChatType get(String value) {
    try {
      return ChatType.valueOf(value.toUpperCase());
    } catch (RuntimeException re) {
      log.warn("unknown ChatType found: {}", value);
      return UNKNOWN;
    }
  }

}
