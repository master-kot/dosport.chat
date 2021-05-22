package ru.dosport.chatservice.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ru.dosport.main.entities.User;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Сущность Сообщение мероприятия
 */
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "event_messages")
public class EventMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private User sender;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @Column(name = "event_id", nullable = false)
    private Long eventId;

    @Column(name = "content", length = 500)
    private String content;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;
}
