package com.indraep.telegram.plugin.bot.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by indraep on 2/26/17.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SendMessageResponse {

    private boolean ok;

    private TelegramMessage result;

    @JsonProperty("error_code")
    private Integer errorCode;

    private String description;
}
