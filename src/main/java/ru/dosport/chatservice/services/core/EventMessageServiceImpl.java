package ru.dosport.chatservice.services.core;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.dosport.chatservice.dtos.MessageResponse;
import ru.dosport.chatservice.dtos.MessageRequest;
import ru.dosport.chatservice.entities.EventMessage;
import ru.dosport.chatservice.mappers.MessageMapper;
import ru.dosport.chatservice.repositories.EventMessageRepository;
import ru.dosport.chatservice.services.api.EventMessageService;
import ru.dosport.main.exceptions.DataBadRequestException;
import ru.dosport.main.exceptions.DataNotFoundException;
import ru.dosport.main.helpers.Roles;
import ru.dosport.main.services.api.UserService;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

import static ru.dosport.main.helpers.InformationMessages.*;

/**
 * Реализация сервиса Сообщений мероприятий
 */
@Service
@RequiredArgsConstructor
public class EventMessageServiceImpl implements EventMessageService {

    // Необходимые зависимости
    private final EventMessageRepository eventMessageRepository;
    private final MessageMapper messageMapper;
    private final UserService userService;

    @Override
    public MessageResponse getDtoById(Long messageId) {
        return messageMapper.mapEntityToDto(findMessageById(messageId));
    }

    @Override
    public List<MessageResponse> getAllDtoByEventId(Long eventId) {
        return messageMapper.mapEventMessageToDto(eventMessageRepository.findAllByEventId(eventId));
    }

    @Transactional
    @Override
    public MessageResponse save(Long eventId, MessageRequest request, Authentication authentication) {
        checkIfEventExists(eventId);
        var message = EventMessage.builder()
                .sender(userService.getById(request.getSenderId()))
                .eventId(eventId)
                .content(request.getContent())
                .creationDate(LocalDateTime.now())
                .build();
        return messageMapper.mapEntityToDto(eventMessageRepository.save(message));
    }

    @Transactional
    @Override
    public MessageResponse update(Long eventId, Long messageId, MessageRequest request, Authentication authentication) {
        var message = findMessageById(messageId);
        checkIfUserIsMessageAuthorOrAdmin(message, authentication);
        if (message.getEventId().equals(eventId)) {
            message.setContent(request.getContent());
            return messageMapper.mapEntityToDto(eventMessageRepository.save(message));
        }
        throw new DataBadRequestException(String.format(MESSAGE_DOES_NOT_BELONG_TO_EVENT, messageId, eventId));
    }

    @Transactional
    @Override
    public boolean deleteById(Long eventId, Long messageId, Authentication authentication) {
        checkIfEventExists(eventId);
        var message = findMessageById(messageId);
        checkIfUserIsMessageAuthorOrAdmin(message, authentication);
        eventMessageRepository.delete(message);
        return !eventMessageRepository.existsById(messageId);
    }

    /**
     * Проверяет является ли пользователь автором сообщения или администратором
     */
    private void checkIfUserIsMessageAuthorOrAdmin(EventMessage eventMessage, Authentication authentication) {
        Long userId = userService.getIdByAuthentication(authentication);
        Long messageId = eventMessage.getSender().getId();
        if (!userId.equals(messageId) | !Roles.hasAuthenticationRoleAdmin(authentication)) {
            throw new DataBadRequestException(String.format(USER_IS_NOT_MESSAGE_AUTHOR, userId, messageId));
        }
    }

    /**
     * Возвращает сообщение по его идентификатору
     */
    private EventMessage findMessageById(Long messageId) {
        return eventMessageRepository.findById(messageId).orElseThrow(
                () -> new DataNotFoundException(String.format(MESSAGE_NOT_FOUND_BY_ID, messageId))
        );
    }

    /**
     * Возвращает существует ли мероприятие по его идентификатору
     */
    private void checkIfEventExists(Long eventId) {
    }
}
