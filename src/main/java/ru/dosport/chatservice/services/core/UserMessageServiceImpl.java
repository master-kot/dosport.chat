package ru.dosport.chatservice.services.core;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.dosport.chatservice.dtos.MessageRequest;
import ru.dosport.chatservice.dtos.MessageResponse;
import ru.dosport.chatservice.entities.UserMessage;
import ru.dosport.chatservice.enums.MessageStatus;
import ru.dosport.chatservice.mappers.MessageMapper;
import ru.dosport.chatservice.repositories.UserMessageRepository;
import ru.dosport.chatservice.services.api.UserMessageService;
import ru.dosport.main.exceptions.DataNotFoundException;
import ru.dosport.main.services.api.UserService;

import java.time.LocalDateTime;
import java.util.List;

import static ru.dosport.main.helpers.InformationMessages.DATA_NOT_FOUND_BY_ID;

/**
 * Реализация сервиса Сообщений пользователей
 */
@Service
@RequiredArgsConstructor
public class UserMessageServiceImpl implements UserMessageService {

    // Необходимые зависимости
    private final UserMessageRepository userMessageRepository;
    private final UserService userService;
    private final MessageMapper messageMapper;

    @Override
    public MessageResponse save(Long recipientId, MessageRequest request, Authentication authentication) {
        UserMessage userMessage = UserMessage.builder()
                .sender(userService.getById(request.getSenderId()))
                .recipientId(recipientId)
                .content(request.getContent())
                .creationDate(LocalDateTime.now())
                .status(MessageStatus.RECEIVED)
                .build();
        return messageMapper.mapEntityToDto(userMessageRepository.save(userMessage));
    }

    @Override
    public List<MessageResponse> getAllByAuthentication(Authentication authentication) {
        Long recipientId = userService.getIdByAuthentication(authentication);
        var messages = userMessageRepository.findAllByRecipientId(recipientId);
        if (messages.size() > 0) {
            updateStatuses(messages, MessageStatus.DELIVERED);
        }
        return messageMapper.mapUserMessageToDto(messages);
    }

    @Override
    public MessageResponse getDtoById(Long id) {
        return userMessageRepository.findById(id)
                .map(messageMapper::mapEntityToDto)
                .orElseThrow(() -> new DataNotFoundException(String.format(DATA_NOT_FOUND_BY_ID, id)));
    }

    private void updateStatuses(List<UserMessage> userMessageList, MessageStatus status) {
        userMessageList.forEach(cm -> cm.setStatus(status));
        userMessageRepository.saveAll(userMessageList);
    }
}
