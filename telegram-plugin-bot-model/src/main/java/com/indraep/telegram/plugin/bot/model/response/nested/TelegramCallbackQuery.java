package com.indraep.telegram.plugin.bot.model.response.nested;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.indraep.telegram.plugin.bot.model.response.TelegramMessage;
import com.indraep.telegram.plugin.bot.model.response.TelegramUser;

/**
 * Created by indraep on 2/26/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TelegramCallbackQuery {

    private String id;
    private TelegramUser from;
    private TelegramMessage message;
    @JsonProperty("inline_message_id")
    private String inlineMessageId;
    @JsonProperty("chat_instance")
    private String chatInstance;
    private String data;
    @JsonProperty("game_short_name")
    private String gameShortName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TelegramUser getFrom() {
        return from;
    }

    public void setFrom(TelegramUser from) {
        this.from = from;
    }

    public TelegramMessage getMessage() {
        return message;
    }

    public void setMessage(TelegramMessage message) {
        this.message = message;
    }

    public String getInlineMessageId() {
        return inlineMessageId;
    }

    public void setInlineMessageId(String inlineMessageId) {
        this.inlineMessageId = inlineMessageId;
    }

    public String getChatInstance() {
        return chatInstance;
    }

    public void setChatInstance(String chatInstance) {
        this.chatInstance = chatInstance;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getGameShortName() {
        return gameShortName;
    }

    public void setGameShortName(String gameShortName) {
        this.gameShortName = gameShortName;
    }

    @Override
    public String toString() {
        return "CallbackQuery{" +
                "id='" + id + '\'' +
                ", from=" + from +
                ", message=" + message +
                ", inlineMessageId='" + inlineMessageId + '\'' +
                ", chatInstance='" + chatInstance + '\'' +
                ", data='" + data + '\'' +
                ", gameShortName='" + gameShortName + '\'' +
                '}';
    }
}
