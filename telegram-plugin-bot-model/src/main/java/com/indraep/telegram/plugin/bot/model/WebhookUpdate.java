package com.indraep.telegram.plugin.bot.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.indraep.telegram.plugin.bot.model.response.TelegramMessage;
import com.indraep.telegram.plugin.bot.model.response.nested.TelegramCallbackQuery;
import com.indraep.telegram.plugin.bot.model.response.nested.TelegramInlineQuery;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WebhookUpdate {

    @JsonProperty("update_id")
    private long updateId;

    private TelegramMessage message;

    @JsonProperty("edited_message")
    private TelegramMessage editedMessage;

    @JsonProperty("channel_post")
    private TelegramMessage channelPost;

    @JsonProperty("edited_channel_post")
    private TelegramMessage editedChannelPost;

    @JsonProperty("inline_query")
    private TelegramInlineQuery inlineQuery;

    @JsonProperty("callback_query")
    private TelegramCallbackQuery callbackQuery;

//    private chosenInlineResult;

}
