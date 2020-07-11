package com.indraep.telegram.plugin.bot.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.indraep.telegram.plugin.bot.model.response.TelegramMessage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * Created by indraep on 2/26/17.
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class TelegramChat {

    private long id;

    private String type;

    private String title;

    private String username;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    private String description;

    @JsonProperty("invite_link")
    private String inviteLink;

    @JsonProperty("pinned_message")
    private TelegramMessage pinnedMessage;

}
