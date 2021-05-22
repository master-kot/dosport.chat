package ru.dosport.chatservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.dosport.chatservice.entities.UserMessage;

import java.util.List;

/**
 * Репозиторий сообщений чатов
 */
public interface UserMessageRepository extends JpaRepository<UserMessage, Long> {

    List<UserMessage> findAllByRecipientId(Long recipientId);
}
