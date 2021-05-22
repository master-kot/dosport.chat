package ru.dosport.chatservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dosport.chatservice.entities.EventMessage;

import java.util.List;

/**
 * Репозиторий сообщений событий
 */
@Repository
public interface EventMessageRepository extends JpaRepository<EventMessage, Long> {

    List<EventMessage> findAllByEventId(Long eventId);
}
