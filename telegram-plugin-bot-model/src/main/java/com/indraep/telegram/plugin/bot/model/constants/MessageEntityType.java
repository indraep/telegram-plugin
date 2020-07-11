package com.indraep.telegram.plugin.bot.model.constants;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public enum MessageEntityType {

  mention, hashtag, cashtag, bot_command, url, email,
  phone_number, bold, italic, underline, strikethrough,
  code, pre, text_link, text_mention,
  unknown;

  static MessageEntityType get(String value) {
    try {
      return MessageEntityType.valueOf(value);
    } catch (RuntimeException rte) {
      log.warn("unknown MessageEntityType found: {}", value);
      return unknown;
    }
  }

}
