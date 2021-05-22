package ru.dosport.chatservice.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Уведомление о новом сообщении чата
 */
@Data
@NoArgsConstructor
public class NotificationResponse {

    private Long messageId;

    private Long senderId;

    private String senderName;
}
