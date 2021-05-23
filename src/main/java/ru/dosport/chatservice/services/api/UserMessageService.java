package ru.dosport.chatservice.services.api;

import org.springframework.security.core.Authentication;
import ru.dosport.chatservice.dtos.MessageRequest;
import ru.dosport.chatservice.dtos.MessageResponse;

import java.util.List;

/**
 * Сервис Сообщений пользователей
 */
public interface UserMessageService {

    MessageResponse save(Long recipientId, MessageRequest request, Authentication authentication);

    List<MessageResponse> getAllByAuthentication(Authentication authentication);

    MessageResponse getDtoById(Long id);
}
