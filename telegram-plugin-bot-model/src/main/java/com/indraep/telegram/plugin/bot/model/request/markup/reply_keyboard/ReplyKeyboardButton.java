package com.indraep.telegram.plugin.bot.model.request.markup.reply_keyboard;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * Created by indraep on 3/26/17.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(builderMethodName = "privateBuilder")
public class ReplyKeyboardButton {
    private String text;

    @JsonProperty("request_contact")
    private boolean requestContact;

    @JsonProperty("request_location")
    private boolean requestLocation;

    public static ReplyKeyboardButtonBuilder builder(String text) {
        return privateBuilder()
            .text(text);
    }

}
