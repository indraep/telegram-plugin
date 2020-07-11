package com.indraep.telegram.plugin.bot.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.indraep.telegram.plugin.bot.model.response.nested.message.TelegramAudioMesssage;
import com.indraep.telegram.plugin.bot.model.response.nested.message.TelegramDocumentMessage;
import com.indraep.telegram.plugin.bot.model.response.nested.message.TelegramMessageEntity;
import com.indraep.telegram.plugin.bot.model.response.nested.message.TelegramPhotoSize;
import com.indraep.telegram.plugin.bot.model.response.nested.message.TelegramVideoMessage;
import com.indraep.telegram.plugin.bot.model.response.nested.message.TelegramVoiceMessage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TelegramMessage {

    @JsonProperty("message_id")
    private long messageId;

    private TelegramUser from;

    private Date date;

    private TelegramChat chat;

    @JsonProperty("forward_from")
    private TelegramUser forwardFrom;

    @JsonProperty("forward_from_chat")
    private TelegramChat forwardFromChat;

    @JsonProperty("forward_from_message_id")
    private long forwardFromMessageId;

    @JsonProperty("forward_signature")
    private String forwardSignature;

    @JsonProperty("forward_sender_name")
    private String forwardSenderName;

    @JsonProperty("forward_date")
    private Date forwardDate;

    @JsonProperty("reply_to_message")
    private TelegramMessage replyToMessage;

    @JsonProperty("edit_date")
    private Date editDate;

    @JsonProperty("media_group_id")
    private String mediaGroupId;

    @JsonProperty("author_signature")
    private String authorSignature;

    private String text;

    private List<TelegramMessageEntity> entities;

    private TelegramAudioMesssage audio;

    private TelegramDocumentMessage document;

    private List<TelegramPhotoSize> photo;

    private TelegramVideoMessage video;

    private TelegramVoiceMessage voice;

    private String caption;

    @JsonProperty("caption_entities")
    private List<TelegramMessageEntity> captionEntities;

    @JsonProperty("new_chat_members")
    private List<TelegramUser> newChatMembers;

    @JsonProperty("left_chat_member")
    private TelegramUser leftChatMember;

    @JsonProperty("new_chat_title")
    private String newChatTitle;

    @JsonProperty("new_chat_photo")
    private List<TelegramPhotoSize> newChatPhoto;

    @JsonProperty("pinned_message")
    private TelegramMessage pinnedMessage;
}
