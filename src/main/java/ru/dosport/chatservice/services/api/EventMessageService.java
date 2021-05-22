package ru.dosport.chatservice.services.api;

import org.springframework.security.core.Authentication;
import ru.dosport.chatservice.dtos.MessageRequest;
import ru.dosport.chatservice.dtos.MessageResponse;

import java.util.List;

public interface EventMessageService {

    /**
     * Возращает сообщение к мероприятию по его идентификатору
     * @param messageId идентификатор мероприятия
     * @return dto сообщения к мероприятию
     */
    MessageResponse getDtoById(Long messageId);

    /**
     * Возращает все сообщения мероприятия
     * @param eventId идентификатор мероприятия
     * @return список dto сообщений мероприятия
     */
    List<MessageResponse> getAllDtoByEventId(Long eventId);

    /**
     * Создаёт новое сообщение к мероприятию
     * @param eventId идентификатор мероприятия
     * @param request текст сообщения
     * @param authentication данные авторизации
     * @return dto нового сообщения к мероприятию, сохраненное в репозитории
     */
    MessageResponse save(Long eventId, MessageRequest request, Authentication authentication);

    /**
     * Обновляет сообщение к мероприятию
     * @param eventId id мероприятия
     * @param messageId id сообщения
     * @param request запрос с новым текстом
     * @param authentication данные авторизации
     * @return dto нового сообщения к мероприятию, обновлённое в репозитории
     */
    MessageResponse update(Long eventId, Long messageId, MessageRequest request, Authentication authentication);

    /**
     * Удаляет сообщение к мероприятию по его идентификатору
     *
     * @param eventId идентификатор мероприятия
     * @param messageId идентификатор сообщение
     * @param authentication данные авторизации
     * @return удалено ли сообщение
     */
    boolean deleteById(Long eventId, Long messageId, Authentication authentication);
}
