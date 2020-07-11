package com.indraep.telegram.plugin.bot.model.response.nested;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.indraep.telegram.plugin.bot.model.response.TelegramUser;

/**
 * Created by indraep on 2/26/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TelegramInlineQuery {

    private String id;
    private TelegramUser sender;
    private String query;
    private String offset;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TelegramUser getSender() {
        return sender;
    }

    public void setSender(TelegramUser sender) {
        this.sender = sender;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }

    @Override
    public String toString() {
        return "InlineQuery{" +
                "id='" + id + '\'' +
                ", sender=" + sender +
                ", query='" + query + '\'' +
                ", offset='" + offset + '\'' +
                '}';
    }
}
