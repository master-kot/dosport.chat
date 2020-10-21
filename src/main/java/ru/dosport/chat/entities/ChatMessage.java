package ru.dosport.chat.entities;

import ru.dosport.chat.helpers.MessageStatus;

import java.util.Date;

public class ChatMessage {
    private Long id;
    private Long chatId;
    private Long senderId;
    private Long recipientId;
    private String senderName;
    private String recipientName;
    private String content;
    private Date timeStamp;
    private MessageStatus messageStatus;
}
