package ru.dosport.chat.services.api;

import java.util.Optional;

public interface ChatRoomService {

    Optional<String> getChatId(String senderId, String recipientId, boolean createIfNoExist);

}
