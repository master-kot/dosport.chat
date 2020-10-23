package ru.dosport.chat.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.dosport.chat.entities.ChatRoom;

import java.util.Optional;

public interface ChatRoomRepository extends MongoRepository<ChatRoom, String> {
    Optional<ChatRoom> findBySenderIdAndRecipientId(String senderId, String recipientId);
}
