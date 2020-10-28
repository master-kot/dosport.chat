package ru.dosport.chat.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.dosport.chat.entities.ChatMessage;
import ru.dosport.chat.helpers.MessageStatus;

import java.util.List;

public interface ChatMessageRepository
        extends MongoRepository<ChatMessage, String> {

    long countBySenderIdAndRecipientIdAndStatus(
            String senderId, String recipientId, MessageStatus status);

    List<ChatMessage> findByChatId(String chatId);
}
