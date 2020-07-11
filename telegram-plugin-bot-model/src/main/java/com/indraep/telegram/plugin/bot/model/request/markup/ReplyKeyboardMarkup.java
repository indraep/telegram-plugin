package com.indraep.telegram.plugin.bot.model.request.markup;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.indraep.telegram.plugin.bot.model.request.markup.reply_keyboard.ReplyKeyboardButton;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * Created by indraep on 3/26/17.
 */
@Getter
@EqualsAndHashCode
@SuperBuilder(builderMethodName = "privateBuilder")
public class ReplyKeyboardMarkup implements ReplyMarkup {

    private List<List<ReplyKeyboardButton>> keyboard;

    @Builder.Default
    @JsonProperty("resize_keyboard")
    private boolean resizeKeyboard = true;

    @Builder.Default
    @JsonProperty("one_time_keyboard")
    private boolean oneTimeKeyboard = true;

    @Builder.Default
    private boolean selective = true;

    public static ReplyKeyboardMarkupBuilder builder(List<List<ReplyKeyboardButton>> keyboard) {
        return privateBuilder()
            .keyboard(keyboard);
    }

}
