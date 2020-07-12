package com.indraep.telegram.plugin.bot.model.request.markup;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.indraep.telegram.plugin.bot.model.request.markup.inline_keyboard.InlineKeyboardButton;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class InlineKeyboardMarkup implements ReplyMarkup {

  @JsonProperty("inline_keyboard")
  private List<List<InlineKeyboardButton>> inlineKeyboard;

}
